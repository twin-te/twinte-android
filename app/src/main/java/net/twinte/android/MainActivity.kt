package net.twinte.android

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.webkit.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sliding_content.*
import net.twinte.android.schedule.ScheduleIndentReceiver
import net.twinte.android.widget.TimetableWidget


const val APP_URL = "https://app.twinte.net"
const val FILE_CHOOSER_REQUEST = 1

class MainActivity : AppCompatActivity() {

    lateinit var cookieManager: CookieManager
    var filePathCallback: ValueCallback<Array<Uri>>? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBarTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScheduleIndentReceiver.enable(this)

        cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(main_webview, true)
        main_webview.settings.javaScriptEnabled = true
        main_webview.settings.domStorageEnabled = true
        main_webview.settings.userAgentString = "TwinteAppforAndroid"
        main_webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                if (!request.url.toString().startsWith(APP_URL) || request.url.toString().startsWith("$APP_URL/auth/")) {
                    initSlidingContent()

                    // GoogleMap対応
                    if (request.url.toString().startsWith("https://www.google.com/maps/")) {
                        startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data = request.url
                        })
                    } else {
                        sliding_layout.isTouchEnabled = false
                        sliding_layout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED

                        external_webview.clearHistory()
                        external_webview.loadUrl(request.url.toString())
                    }

                    true
                } else {
                    view.loadUrl(request.url.toString())
                    false
                }
        }
        main_webview.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                this@MainActivity.filePathCallback = filePathCallback
                startActivityForResult(fileChooserParams.createIntent(), FILE_CHOOSER_REQUEST)
                return true
            }
        }
        main_webview.addJavascriptInterface(object {
            @JavascriptInterface()
            fun openSettings() {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }

            @JavascriptInterface()
            fun share(body: String) {
                main_webview.shareScreen(body)
            }
        }, "android")

        // ウィジットタップから起動した場合、タップした講義のuserLectureIdが入る、それ以外はnull
        val userLectureId = intent.getStringExtra("user_lecture_id")
        main_webview.loadUrl(if (userLectureId != null && userLectureId.isNotEmpty()) "$APP_URL/course/${userLectureId}" else APP_URL)
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
        external_webview.settings.userAgentString =
            "Mozilla/5.0 (Linux; Android ${android.os.Build.VERSION.RELEASE}; ${android.os.Build.MODEL}) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Mobile Safari/537.36"
        cookieManager.setAcceptThirdPartyCookies(external_webview, true)

        external_webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                if (request.url.toString().startsWith(APP_URL) && !request.url.toString().startsWith("$APP_URL/auth/")) {
                    cookieManager.flush()
                    closePanel()
                    main_webview.loadUrl(APP_URL)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILE_CHOOSER_REQUEST) {
            if (resultCode == RESULT_OK)
                filePathCallback?.onReceiveValue(if (data?.data != null) arrayOf(data.data!!) else null)
            else
                filePathCallback?.onReceiveValue(null)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // ウィジットタップから起動した場合、タップした講義のuserLectureIdが入る、それ以外はnull
        intent.getStringExtra("user_lecture_id")?.let { userLectureId ->
            main_webview.loadUrl(if (userLectureId.isNotEmpty()) "$APP_URL/course/${userLectureId}" else APP_URL)
        }
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
