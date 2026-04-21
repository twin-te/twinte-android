package net.twinte.android

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat
import net.twinte.android.network.serversettings.ServerSettings

class MainWebViewController(
    private val cookieManager: CookieManager,
    private val serverSettings: ServerSettings,
    private val onGoogleSignInRequest: () -> Unit,
    private val onOpenExternalIntentRequest: (Intent) -> Unit,
    private val onOpenSubWebViewRequest: (String) -> Unit,
    private val onShowFileChooserRequest: (ValueCallback<Array<Uri>>, WebChromeClient.FileChooserParams) -> Unit,
    private val onOpenSettingsRequest: () -> Unit,
    private val onShareRequest: (String) -> Unit,
) {
    @SuppressLint("SetJavaScriptEnabled")
    fun attach(webView: WebView) {
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.userAgentString = "TwinteAppforAndroid"
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        webView.webViewClient = object : WebViewClientCompat() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                when {
                    request.url.host == "accounts.google.com" -> {
                        onGoogleSignInRequest()
                        true
                    }
                    request.url.toString().startsWith("https://www.google.com/maps") -> {
                        onOpenExternalIntentRequest(
                            Intent(Intent.ACTION_VIEW).apply {
                                data = request.url
                            },
                        )
                        true
                    }
                    request.url.host != serverSettings.twinteBackendApiEndpointHost -> {
                        onOpenSubWebViewRequest(request.url.toString())
                        true
                    }
                    else -> false
                }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams,
            ): Boolean {
                onShowFileChooserRequest(filePathCallback, fileChooserParams)
                return true
            }
        }

        webView.addJavascriptInterface(
            MainJavascriptBridge(
                onOpenSettingsRequest = onOpenSettingsRequest,
                onShareRequest = onShareRequest,
            ),
            "android",
        )
    }
}

private class MainJavascriptBridge(
    private val onOpenSettingsRequest: () -> Unit,
    private val onShareRequest: (String) -> Unit,
) {
    @JavascriptInterface
    fun openSettings() {
        onOpenSettingsRequest()
    }

    @JavascriptInterface
    fun share(body: String) {
        onShareRequest(body)
    }
}
