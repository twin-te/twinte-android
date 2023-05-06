package net.twinte.android

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewFeature
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.twinte.android.Network.WebViewCookieJar.cookieManager
import net.twinte.android.repository.ScheduleRepository
import net.twinte.android.repository.TwinteBackendUserRepository
import net.twinte.android.widget.WidgetUpdater
import net.twinte.android.work.ScheduleNotifier
import net.twinte.android.work.UpdateScheduleWorker
import javax.inject.Inject

const val TWINTE_DEBUG = false

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SubWebViewFragment.Callback {
    var filePathCallback: ValueCallback<Array<Uri>>? = null

    @Inject
    lateinit var scheduleRepository: ScheduleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_Main)
        setContentView(R.layout.activity_main)

        UpdateScheduleWorker.scheduleNextUpdate(this)
        ScheduleNotifier.schedule(this)
        GlobalScope.launch {
            try {
                scheduleRepository.update()
            } catch (e: Network.NotLoggedInException) {
                // 未ログイン時は失敗するが何もしない
            } catch (e: Exception) {
                // それ以外の予期せぬエラー
            }
            WidgetUpdater.updateAllWidget(this@MainActivity)
            WidgetUpdater.scheduleAllIfExists(this@MainActivity)
        }

        if (TWINTE_DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        main_webview.setup()

        val url = intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let { twinteUrlBuilder().appendPath("course").appendPath(it).buildUrl() }
            ?: twinteUrlBuilder().buildUrl()

        main_webview.loadUrl(url)
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
                    request.url.host != DOMAIN -> {
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
            WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK) &&
            WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)
        ) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                // ダークモードサポートだったら
                Configuration.UI_MODE_NIGHT_YES -> {
                    // ダークモード有効
                    WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_ON)
                    // ダークモードのスタイリングはページが行う
                    WebSettingsCompat.setForceDarkStrategy(
                        settings,
                        WebSettingsCompat.DARK_STRATEGY_WEB_THEME_DARKENING_ONLY,
                    )
                }
                Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                    WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_OFF)
                }
            }
        }
        addJavascriptInterface(
            object {
                @JavascriptInterface
                fun openSettings() {
                    startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                }

                @JavascriptInterface
                fun share(body: String) {
                    main_webview.shareScreen(body)
                }
            },
            "android",
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Googleログイン時
            RC_SIGN_IN -> {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                GlobalScope.launch {
                    account?.idToken?.let {
                        TwinteBackendUserRepository().validateGoogleIdToken(it)
                    }
                    withContext(Dispatchers.Main) {
                        main_webview.loadUrl(twinteUrlBuilder().buildUrl())
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
            try {
                scheduleRepository.update()
            } catch (e: Network.NotLoggedInException) {
                // 未ログイン時は失敗するが何もしない
            }
            WidgetUpdater.updateAllWidget(this@MainActivity)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.getStringExtra("REGISTERED_COURSE_ID")
            ?.let {
                main_webview.loadUrl(twinteUrlBuilder().appendPath("course").appendPath(it).buildUrl())
            }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is SubWebViewFragment) {
            fragment.callback = this
        }
    }

    override fun onBackPressed() {
        if (main_webview.canGoBack()) {
            main_webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // SubWebViewでMainWebViewに読み込ませたくなった時に呼び出される
    override fun subWebViewCallback(url: String) {
        main_webview.loadUrl(url)
    }

    companion object {
        const val RC_SIGN_IN = 1
        const val FILE_CHOOSER_REQUEST = 2
    }
}
