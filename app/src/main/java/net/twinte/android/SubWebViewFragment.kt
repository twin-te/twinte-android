package net.twinte.android

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
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
import dagger.hilt.android.AndroidEntryPoint
import net.twinte.android.databinding.FragmentSubWebviewBinding
import net.twinte.android.network.serversettings.ServerSettings
import javax.inject.Inject

@AndroidEntryPoint
class SubWebViewFragment : BottomSheetDialogFragment() {
    var callback: Callback? = null

    @Inject
    lateinit var cookieManager: CookieManager

    @Inject
    lateinit var serverSettings: ServerSettings

    private var _binding: FragmentSubWebviewBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (dialog as? BottomSheetDialog)?.behavior?.let { behavior ->
            behavior.addBottomSheetCallback(
                object : BottomSheetBehavior.BottomSheetCallback() {

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_DRAGGING && binding.subWebview.scrollY > 0) {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }
                },
            )
        }
        binding.subWebview.apply {
            settings.javaScriptEnabled = true
            cookieManager.setAcceptThirdPartyCookies(this, true)
            webViewClient = object : WebViewClientCompat() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                    // Twin:teのアプリケーションページに飛ぶときはダイアログを閉じてメインで表示
                    if (
                        request.url.host == serverSettings.twinteBackendApiEndpointHost &&
                        request.url.path?.startsWith("/api/v3") != true &&
                        request.url.path?.startsWith("/auth/v3") != true
                    ) {
                        callback?.subWebViewCallback(request.url.toString())
                        dismiss()
                        true
                    } else {
                        false
                    }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    binding.subWebviewProgressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView, url: String) {
                    view.layoutParams =
                        FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            if (view.height < 200.toPx()) 300.toPx() else FrameLayout.LayoutParams.MATCH_PARENT,
                        )
                    binding.subWebviewProgressBar.visibility = View.GONE
                    // Twinsからインポート
                    if (url.startsWith("https://twins.tsukuba.ac.jp")) {
                        view.evaluateJavascript(
                            """
                        var script = document.createElement('script');
                        script.src = 'https://scripts.twinte.net/sp.js';
                        document.head.appendChild(script);
                            """.trimIndent(),
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        object : BottomSheetDialog(requireContext(), theme) {
            @Deprecated("Deprecated in Java")
            override fun onBackPressed() {
                if (binding.subWebview.canGoBack()) {
                    // ダイアログで表示されているページから一つ前のページに戻れる場合、そこに戻る
                    binding.subWebview.goBack()
                } else {
                    // ダイアログで表示されているページの前にはページが無い場合、ダイアログを閉じる
                    super.onBackPressed()
                }
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSubWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root.removeView(binding.subWebview)
        binding.subWebview.destroy()
        _binding = null
    }

    interface Callback {
        fun subWebViewCallback(url: String)
    }

    fun Int.toPx() = ((context?.resources?.displayMetrics?.density ?: 1f) * this).toInt()

    companion object {
        fun open(url: String, manager: FragmentManager) = SubWebViewFragment().apply {
            arguments = Bundle().apply { putString("url", url) }
            show(manager, "sub_webview")
        }
    }
}
