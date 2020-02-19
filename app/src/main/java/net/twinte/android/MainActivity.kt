package net.twinte.android

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.webkit.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sliding_content.*
import net.twinte.android.widget.TimetableWidget


class MainActivity : AppCompatActivity() {

    lateinit var cookieManager: CookieManager

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBarTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(main_webview, true)

        main_webview.settings.javaScriptEnabled = true
        main_webview.settings.domStorageEnabled = true
        main_webview.settings.userAgentString = "TwinteAppforAndroid"
        main_webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                if (!request.url.toString().startsWith("https://app.twinte.net")) {
                    initSlidingContent()

                    sliding_layout.isTouchEnabled = false
                    sliding_layout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED

                    external_webview.settings.userAgentString =
                        if (request.url.toString().endsWith("/auth/google")) "TwinteAppforAndroid" else null
                    external_webview.clearHistory()
                    external_webview.loadUrl(request.url.toString())
                    true
                } else {
                    view.loadUrl(request.url.toString())
                    false
                }
        }
        main_webview.addJavascriptInterface(object {
            @JavascriptInterface()
            fun openSettings() {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }
        }, "android")

        // ウィジットタップから起動した場合、タップした講義のuserLectureIdが入る、それ以外はnull
        val userLectureId = intent.getStringExtra("user_lecture_id")

        main_webview.loadUrl(if (userLectureId != null) "https://app.twinte.net?user_lecture_id=${userLectureId}" else "https://app.twinte.net")
    }

    /**
     * 起動時に必要ない部分は遅延読み込み
     */
    @SuppressLint("SetJavaScriptEnabled")
    fun initSlidingContent() {
        if (stub == null) return

        stub.visibility = View.VISIBLE

        external_webview.settings.javaScriptEnabled = true
        external_webview.settings.domStorageEnabled = true
        cookieManager.setAcceptThirdPartyCookies(external_webview, true)

        external_webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                if (request.url.toString().startsWith("https://app.twinte.net")) {
                    cookieManager.flush()
                    closePanel()
                    main_webview.reload()
                    updateWidgets()
                    true
                } else {
                    false
                }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                external_progressbar.startAnimation(AlphaAnimation(1f, 0f).apply { duration = 300 })
                view.startAnimation(AlphaAnimation(0.5f, 1f).apply { duration = 300 })
                view.alpha = 1f
                external_progressbar.visibility = View.GONE
                external_title_textview.text = view.title
                external_url_textview.text = view.url
                if (url.startsWith("https://twins.tsukuba.ac.jp")) {
                    external_webview.evaluateJavascript(
                        """
                        var script = document.createElement('script');
                        script.src = 'https://scripts.twinte.net/sp.js';
                        document.head.appendChild(script);
                        """.trimIndent()
                    ) {}
                }
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                external_progressbar.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 300 })
                view.startAnimation(AlphaAnimation(1f, 0.5f).apply { duration = 300 })
                view.alpha = 0.5f
                external_progressbar.visibility = View.VISIBLE
            }
        }

        external_webview.webChromeClient = object : WebChromeClient() {
            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage(message)
                    .setPositiveButton("OK") { _, _ -> result.confirm() }
                    .setNegativeButton("Cancel") { _, _ -> result.cancel() }
                    .show()
                return true
            }

            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage(message)
                    .setPositiveButton("OK") { _, _ -> result.confirm() }
                    .show()
                return true
            }
        }

        external_close_button.setOnClickListener {
            closePanel()
            if (external_webview.url.startsWith("https://twins.tsukuba.ac.jp/")) {
                main_webview.reload()
                updateWidgets()
            }
        }
    }

    override fun onBackPressed() {
        if (sliding_layout.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            if (external_webview.canGoBack()) external_webview.goBack()
            else closePanel()
        } else if (main_webview.canGoBack()) main_webview.goBack()
        else super.onBackPressed()
    }

    fun closePanel() {
        sliding_layout.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
        external_webview.clearHistory()
        external_webview.alpha = 0f
    }

    private fun updateWidgets() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        sendBroadcast(Intent(this, TimetableWidget::class.java).apply {
            action = "android.appwidget.action.APPWIDGET_UPDATE"
            putExtra(
                AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetManager.getAppWidgetIds(
                    ComponentName(
                        application,
                        TimetableWidget::class.java
                    )
                )
            )
        })
    }
}
