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
import androidx.activity.viewModels
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
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
    private lateinit var mainWebViewController: MainWebViewController
    private val mainViewModel: MainViewModel by viewModels()

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
            onPageLoadingChanged = mainViewModel::onPageLoadingChanged,
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
            val uiState by mainViewModel.uiState.collectAsState()
            MainScreen(
                initialUrl = url,
                uiState = uiState,
            )
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
    private fun MainScreen(
        initialUrl: String,
        uiState: MainUiState,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.background)),
        ) {
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

            if (uiState.isPageLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    color = colorResource(R.color.colorPrimary),
                )
            }

            if (uiState.isGoogleSignInInProgress) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(20.dp),
                            ),
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 28.dp, vertical = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            CircularProgressIndicator(color = colorResource(R.color.colorPrimary))
                            Text(
                                text = "Googleアカウントを確認中",
                                modifier = Modifier.padding(top = 12.dp),
                                color = colorResource(R.color.widget_text_main),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun startGoogleSignIn() {
        if (mainViewModel.uiState.value.isGoogleSignInInProgress) {
            return
        }

        lifecycleScope.launch {
            mainViewModel.onGoogleSignInStarted()
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
                mainViewModel.onGoogleSignInFinished()
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
