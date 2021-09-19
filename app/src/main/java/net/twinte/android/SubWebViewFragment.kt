package net.twinte.android

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.webkit.WebViewClientCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_sub_webview.*
import net.twinte.android.Network.WebViewCookieJar.cookieManager

class SubWebViewFragment : BottomSheetDialogFragment() {
    var callback: Callback? = null

    companion object {
        fun open(url: String, manager: FragmentManager) = SubWebViewFragment().apply {
            arguments = Bundle().apply { putString("url", url) }
            show(manager, "sub_webview")
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (dialog as? BottomSheetDialog)?.behavior?.let { behavior ->
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING && sub_webview.scrollY > 0) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            })

        }
        sub_webview.apply {
            settings.javaScriptEnabled = true
            cookieManager.setAcceptThirdPartyCookies(this, true)
            webViewClient = object : WebViewClientCompat() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                    // Twin:teのアプリケーションページに飛ぶときはダイアログを閉じてメインで表示
                    if (
                        request.url.host == DOMAIN &&
                        request.url.path?.startsWith(API_PATH) != true &&
                        request.url.path?.startsWith(AUTH_PATH) != true
                    ) {
                        callback?.subWebViewCallback(request.url.toString())
                        dismiss()
                        true
                    } else false

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    sub_webview_progressBar?.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView, url: String) {
                    view.layoutParams =
                        FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            if (view.height < 200.toPx()) 300.toPx() else FrameLayout.LayoutParams.MATCH_PARENT
                        )
                    sub_webview_progressBar?.visibility = View.GONE
                    // Twinsからインポート
                    if (url.startsWith("https://twins.tsukuba.ac.jp")) {
                        view.evaluateJavascript(
                            """
                        var script = document.createElement('script');
                        script.src = 'https://scripts.twinte.net/sp.js';
                        document.head.appendChild(script);
                        """.trimIndent()
                        ) {}
                    }
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
                    AlertDialog.Builder(context)
                        .setMessage(message)
                        .setPositiveButton("OK") { _, _ -> result.confirm() }
                        .setNegativeButton("Cancel") { _, _ -> result.cancel() }
                        .show()
                    return true
                }

                override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                    AlertDialog.Builder(context)
                        .setMessage(message)
                        .setPositiveButton("OK") { _, _ -> result.confirm() }
                        .show()
                    return true
                }
            }
            loadUrl(arguments?.getString("url", "") ?: "")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnKeyListener { _, code, event ->
            if (event.action != KeyEvent.ACTION_UP) return@setOnKeyListener true

            if (code == KeyEvent.KEYCODE_BACK && sub_webview.canGoBack())
                sub_webview.goBack()
            else dialog.dismiss()

            return@setOnKeyListener true
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_webview, container, false)
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (sub_webview.url?.startsWith("https://twins.tsukuba.ac.jp") == true)
            callback?.subWebViewCallback(twinteUrlBuilder().buildUrl())
        super.onDismiss(dialog)
    }

    interface Callback {
        fun subWebViewCallback(url: String)
    }

    fun Int.toPx() = ((context?.resources?.displayMetrics?.density ?: 1f) * this).toInt()
}
