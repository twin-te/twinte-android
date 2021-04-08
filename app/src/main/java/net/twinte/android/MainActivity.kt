package net.twinte.android

import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewFeature
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_Main)
        setContentView(R.layout.activity_main)
        main_webview.setup()
        main_webview.loadUrl("https://app.twinte.net/")
    }

    private fun WebView.setup() {
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.userAgentString = "TwinteAppforAndroid"
        webViewClient = object : WebViewClientCompat() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = false
        }
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {

            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                // ダークモードサポートだったら
                Configuration.UI_MODE_NIGHT_YES -> {
                    // ダークモード有効
                    WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_ON)
                    // ダークモードのスタイリングはページが行う
                    WebSettingsCompat.setForceDarkStrategy(settings, WebSettingsCompat.DARK_STRATEGY_WEB_THEME_DARKENING_ONLY)
                }
                Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                    WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_OFF)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (main_webview.canGoBack()) main_webview.goBack()
        else
            super.onBackPressed()
    }
}
