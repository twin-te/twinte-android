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
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.twinte.android.databinding.ActivityMainBinding
import net.twinte.android.network.serversettings.ServerSettings
import javax.inject.Inject

const val TWINTE_DEBUG = false

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SubWebViewFragment.Callback {
    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private var mainWebView: WebView? = null
    private var isGoogleSignInInProgress = false
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

    private val fileChooserLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleFileChooserResult(result)
        }

    @Inject
    lateinit var mainActivityEffects: MainActivityEffects

    @Inject
    lateinit var googleIdTokenAuthenticator: GoogleIdTokenAuthenticator

    @Inject
    lateinit var cookieManager: CookieManager

    @Inject
    lateinit var serverSettings: ServerSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_Main)

        val url = intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let { twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl() }
            ?: twinteUrlBuilder(serverSettings).buildUrl()

        mainWebViewController = MainWebViewController(
            cookieManager = cookieManager,
            serverSettings = serverSettings,
            onGoogleSignInRequest = ::startGoogleSignIn,
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

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (mainWebView?.canGoBack() == true) {
                        mainWebView?.goBack()
                    } else {
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            },
        )

        mainActivityEffects.prepareLaunch()
        lifecycleScope.launch {
            mainActivityEffects.refreshAfterLaunch(applicationContext)
        }

        if (TWINTE_DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
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

    private fun startGoogleSignIn() {
        if (isGoogleSignInInProgress) {
            return
        }

        lifecycleScope.launch {
            isGoogleSignInInProgress = true
            try {
                val idToken = googleIdTokenAuthenticator.requestIdToken(
                    activity = this@MainActivity,
                    serverClientId = getString(R.string.google_server_client_id),
                )

                if (idToken == null) {
                    // Web 側で blur がかかったままになるため元ページを描き直す
                    mainWebView?.reload()
                    return@launch
                }

                if (!mainActivityEffects.submitGoogleIdToken(idToken)) {
                    Toast.makeText(applicationContext, R.string.common_google_play_services_unknown_issue, Toast.LENGTH_SHORT).show()
                }
                mainWebView?.loadUrl(twinteUrlBuilder(serverSettings).buildUrl())
            } finally {
                isGoogleSignInInProgress = false
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
        mainActivityEffects.flushCookies()
        lifecycleScope.launch {
            mainActivityEffects.refreshOnPause(applicationContext)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let {
                mainWebView?.loadUrl(twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl())
            }
    }

    // SubWebViewでMainWebViewに読み込ませたくなった時に呼び出される
    override fun subWebViewCallback(url: String) {
        mainWebView?.loadUrl(url)
    }
}
