package net.twinte.android

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.fragment.app.FragmentManager
import androidx.webkit.WebViewClientCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_sub_webview.*

class SubWebViewFragment : BottomSheetDialogFragment() {
    var positionY = 0

    companion object {
        fun open(url: String, manager: FragmentManager) = SubWebViewFragment().apply {
            arguments = Bundle().apply { putString("url", url) }
            show(manager, "sub_webview")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (dialog as? BottomSheetDialog)?.behavior?.let { behavior ->
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING && positionY > 0) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            })

        }
        sub_webview.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClientCompat() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = false
            }
            loadUrl(arguments?.getString("url", "") ?: "")
            setOnScrollChangeListener { _, _, y, _, _ ->
                positionY = y
            }
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
}
