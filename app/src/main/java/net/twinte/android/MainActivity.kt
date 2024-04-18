package net.twinte.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewFeature
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    var filePathCallback: ValueCallback<Array<Uri>>? = null

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

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.AppTheme_Main)
        setContentView(binding.root)

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

        binding.mainWebview.setup()

        val url = intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let { twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl() }
            ?: twinteUrlBuilder(serverSettings).buildUrl()

        binding.mainWebview.loadUrl(url)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (!isGranted) {
                        Snackbar.make(
                            binding.root,
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
                }.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            return
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebView.setup() {
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.userAgentString = "TwinteAppforAndroid"
        cookieManager.setAcceptThirdPartyCookies(this, true)
        webViewClient = object : WebViewClientCompat() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                when {
                    // Googleログイン
                    request.url.host == "accounts.google.com" -> {
                        val clientId = getString(R.string.google_server_client_id)
                        val signInClient = GoogleSignIn.getClient(
                            this@MainActivity,
                            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(clientId).build(),
                        )
                        startActivityForResult(signInClient.signInIntent, RC_SIGN_IN)
                        true
                    }
                    // GoogleMap対応
                    request.url.toString().startsWith("https://www.google.com/maps") -> {
                        startActivity(
                            Intent(Intent.ACTION_VIEW).apply {
                                data = request.url
                            },
                        )
                        true
                    }
                    // その他の外部サイト
                    request.url.host != serverSettings.twinteBackendApiEndpointHost -> {
                        SubWebViewFragment.open(request.url.toString(), supportFragmentManager)
                        true
                    }
                    else -> false
                }
        }

        webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams,
            ): Boolean {
                this@MainActivity.filePathCallback = filePathCallback
                startActivityForResult(fileChooserParams.createIntent(), FILE_CHOOSER_REQUEST)
                return true
            }
        }

        if (
            WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)
        ) {
            WebSettingsCompat.setAlgorithmicDarkeningAllowed(settings, true)
        }
        addJavascriptInterface(
            object {
                @JavascriptInterface
                fun openSettings() {
                    startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                }

                @JavascriptInterface
                fun share(body: String) {
                    binding.mainWebview.shareScreen(body)
                }
            },
            "android",
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Googleログイン時
            RC_SIGN_IN -> {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                GlobalScope.launch {
                    account?.idToken?.let {
                        kotlin.runCatching { userDataStore.validateGoogleIdToken(it) }
                            .onFailure {
                                Toast.makeText(applicationContext, R.string.common_google_play_services_unknown_issue, Toast.LENGTH_SHORT).show()
                            }
                    }
                    withContext(Dispatchers.Main) {
                        binding.mainWebview.loadUrl(twinteUrlBuilder(serverSettings).buildUrl())
                    }
                }
            }
            // ファイル選択時
            FILE_CHOOSER_REQUEST -> {
                if (resultCode == RESULT_OK) {
                    filePathCallback?.onReceiveValue(if (data?.data != null) arrayOf(data.data!!) else null)
                } else {
                    filePathCallback?.onReceiveValue(null)
                }
            }
        }
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
                binding.mainWebview.loadUrl(twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl())
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
        if (binding.mainWebview.canGoBack()) {
            binding.mainWebview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // SubWebViewでMainWebViewに読み込ませたくなった時に呼び出される
    override fun subWebViewCallback(url: String) {
        binding.mainWebview.loadUrl(url)
    }

    companion object {
        const val RC_SIGN_IN = 1
        const val FILE_CHOOSER_REQUEST = 2
    }
}
