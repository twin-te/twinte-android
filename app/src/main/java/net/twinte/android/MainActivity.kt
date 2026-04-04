package net.twinte.android

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.twinte.android.databinding.ActivityMainBinding
import net.twinte.android.datastore.resetcookiesforsamesite.ResetCookiesForSameSiteDataStore
import net.twinte.android.datastore.schedule.ScheduleDataStore
import net.twinte.android.datastore.schedulenotification.ScheduleNotificationDataStore
import net.twinte.android.datastore.user.UserDataStore
import net.twinte.android.network.serversettings.ServerSettings
import net.twinte.android.widget.WidgetUpdater
import net.twinte.android.work.UpdateScheduleWorker
import javax.inject.Inject

const val TWINTE_DEBUG = false

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SubWebViewFragment.Callback {
    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private var mainWebView: WebView? = null
    private lateinit var mainWebViewController: MainWebViewController

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Snackbar.make(
                    findViewById<View>(android.R.id.content),
                    getString(R.string.snackbar_notification_not_granted_text),
                    Snackbar.LENGTH_LONG,
                ).setAction(getString(R.string.snackbar_notification_not_granted_settings)) {
                    startActivity(
                        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra(Settings.EXTRA_APP_PACKAGE, packageName),
                    )
                }.show()
            }
        }

    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleGoogleSignInResult(result)
        }

    private val fileChooserLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleFileChooserResult(result)
        }

    @Inject
    lateinit var scheduleDataStore: ScheduleDataStore

    @Inject
    lateinit var userDataStore: UserDataStore

    @Inject
    lateinit var scheduleNotificationDataStore: ScheduleNotificationDataStore

    @Inject
    lateinit var resetCookiesForSameSiteDataStore: ResetCookiesForSameSiteDataStore

    @Inject
    lateinit var cookieManager: CookieManager

    @Inject
    lateinit var serverSettings: ServerSettings

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_Main)

        val url = intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let { twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl() }
            ?: twinteUrlBuilder(serverSettings).buildUrl()

        mainWebViewController = MainWebViewController(
            cookieManager = cookieManager,
            serverSettings = serverSettings,
            googleServerClientId = getString(R.string.google_server_client_id),
            onGoogleSignInRequest = googleSignInLauncher::launch,
            onOpenExternalIntentRequest = ::startActivity,
            onOpenSubWebViewRequest = { urlToOpen ->
                SubWebViewFragment.open(urlToOpen, supportFragmentManager)
            },
            onShowFileChooserRequest = { callback, params ->
                filePathCallback = callback
                fileChooserLauncher.launch(params.createIntent())
            },
            onOpenSettingsRequest = {
                startActivity(Intent(this, SettingsActivity::class.java))
            },
            onShareRequest = { body ->
                mainWebView?.shareScreen(body)
            },
        )

        setContent {
            MainWebView(initialUrl = url)
        }

        UpdateScheduleWorker.scheduleNextUpdate(workManager)
        scheduleNotificationDataStore.schedule()
        GlobalScope.launch {
            kotlin.runCatching { scheduleDataStore.update() }
                .fold(onSuccess = {}, onFailure = {
                    when (it) {
                        is NotLoggedInException -> {
                            // 未ログイン時は失敗するが何もしない
                        }
                        else -> {
                            // それ以外の予期せぬエラー
                        }
                    }
                })
            WidgetUpdater.updateAllWidget(this@MainActivity)
            WidgetUpdater.scheduleAllIfExists(this@MainActivity)
        }

        if (TWINTE_DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        // ref: https://github.com/twin-te/twinte-android/issues/44
        // TODO: リリースしてから一年経過したら削除する（twinte_session は元々得られてから 1 年後に expire する設定になっている）
        if (resetCookiesForSameSiteDataStore.shouldResetCookiesForSameSite) {
            cookieManager.removeAllCookies {}
            resetCookiesForSameSiteDataStore.shouldResetCookiesForSameSite = false
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            return
        }
    }

    @Composable
    private fun MainWebView(initialUrl: String) {
        AndroidViewBinding(ActivityMainBinding::inflate) {
            if (mainWebView !== mainWebview) {
                mainWebView = mainWebview
                mainWebViewController.attach(mainWebview)
            }
            if (mainWebview.url == null) {
                mainWebview.post {
                    if (mainWebview.url == null) {
                        mainWebview.loadUrl(initialUrl)
                    }
                }
            }
        }
    }

    private fun handleGoogleSignInResult(result: ActivityResult) {
        val signInTask = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        val account = signInTask
            .runCatching { signInTask.result }
            .fold(onSuccess = { it }, onFailure = {
                // blur がかかったままなためリロードする
                mainWebView?.reload()
                return
            })
        GlobalScope.launch {
            account?.idToken?.let {
                kotlin.runCatching { userDataStore.validateGoogleIdToken(it) }
                    .onFailure {
                        Toast.makeText(applicationContext, R.string.common_google_play_services_unknown_issue, Toast.LENGTH_SHORT).show()
                    }
            }
            withContext(Dispatchers.Main) {
                mainWebView?.loadUrl(twinteUrlBuilder(serverSettings).buildUrl())
            }
        }
    }

    private fun handleFileChooserResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            filePathCallback?.onReceiveValue(
                if (result.data?.data != null) {
                    arrayOf(result.data!!.data!!)
                } else {
                    null
                },
            )
        } else {
            filePathCallback?.onReceiveValue(null)
        }
        filePathCallback = null
    }

    override fun onPause() {
        super.onPause()
        cookieManager.flush()
        GlobalScope.launch {
            kotlin.runCatching {
                scheduleDataStore.update()
            }.fold(onSuccess = {}, onFailure = { e ->
                when (e) {
                    is NotLoggedInException -> {
                        // 未ログイン時は失敗するが何もしない
                    }
                    else -> { }
                }
            })
            WidgetUpdater.updateAllWidget(this@MainActivity)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let {
                mainWebView?.loadUrl(twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl())
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is SubWebViewFragment) {
            fragment.callback = this
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (mainWebView?.canGoBack() == true) {
            mainWebView?.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // SubWebViewでMainWebViewに読み込ませたくなった時に呼び出される
    override fun subWebViewCallback(url: String) {
        mainWebView?.loadUrl(url)
    }
}
