package net.twinte.android

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.twinte.android.databinding.ActivityMainBinding
import net.twinte.android.network.serversettings.ServerSettings
import javax.inject.Inject
import androidx.core.net.toUri

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
                mainViewModel.showMessage(
                    text = getString(R.string.snackbar_notification_not_granted_text),
                    actionLabel = getString(R.string.snackbar_notification_not_granted_settings),
                    action = MainUiMessageAction.OpenNotificationSettings,
                )
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

    @RequiresApi(Build.VERSION_CODES.O)
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
            onPageLoadError = mainViewModel::onPageLoadError,
            onGoogleSignInRequest = ::startGoogleSignIn,
            onOpenExternalUrlRequest = { url ->
                mainViewModel.emitEvent(MainUiEventPayload.OpenExternalUrl(url))
            },
            onOpenSubWebViewRequest = { urlToOpen ->
                mainViewModel.emitEvent(MainUiEventPayload.OpenSubWebView(urlToOpen))
            },
            onShowFileChooserRequest = { callback, params ->
                filePathCallback = callback
                fileChooserLauncher.launch(params.createIntent())
            },
            onOpenSettingsRequest = {
                mainViewModel.emitEvent(MainUiEventPayload.OpenSettings)
            },
            onShareRequest = { body ->
                mainViewModel.emitEvent(MainUiEventPayload.Share(body))
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

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun MainScreen(
        initialUrl: String,
        uiState: MainUiState,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(uiState.message?.id) {
            val message = uiState.message ?: return@LaunchedEffect
            val result = snackbarHostState.showSnackbar(
                message = message.text,
                actionLabel = message.actionLabel,
                duration = SnackbarDuration.Long,
            )
            if (result == SnackbarResult.ActionPerformed) {
                handleMainMessageAction(message.action)
            }
            mainViewModel.clearMessage(message.id)
        }

        LaunchedEffect(uiState.event?.id) {
            val event = uiState.event ?: return@LaunchedEffect
            handleMainUiEvent(event.payload)
            mainViewModel.clearEvent(event.id)
        }

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
                            mainWebViewController.onMainFrameLoadRequested()
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

            if (uiState.pageLoadErrorMessage != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.08f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .background(
                                color = MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(20.dp),
                            )
                            .padding(horizontal = 24.dp, vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = uiState.pageLoadErrorMessage,
                            color = colorResource(R.color.widget_text_main),
                        )
                        Button(
                            onClick = {
                                mainViewModel.clearPageLoadError()
                                mainWebView?.reload()
                            },
                            modifier = Modifier.padding(top = 16.dp),
                        ) {
                            Text(stringResource(R.string.main_reload))
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (uiState.isGoogleSignInInProgress) {
                            Color.Black.copy(alpha = 0.16f)
                        } else {
                            Color.Transparent
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                if (uiState.isGoogleSignInInProgress) {
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
                                text = stringResource(R.string.main_google_sign_in_in_progress),
                                modifier = Modifier.padding(top = 12.dp),
                                color = colorResource(R.color.widget_text_main),
                            )
                        }
                    }
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
            )
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
                    mainViewModel.showMessage(getString(R.string.main_google_sign_in_failed))
                    mainWebView?.reload()
                    return@launch
                }

                if (!mainActivityEffects.submitGoogleIdToken(idToken)) {
                    mainViewModel.showMessage(getString(R.string.common_google_play_services_unknown_issue))
                }
                mainViewModel.clearPageLoadError()
                mainViewModel.emitEvent(
                    MainUiEventPayload.LoadUrl(twinteUrlBuilder(serverSettings).buildUrl()),
                )
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
                mainViewModel.clearPageLoadError()
                mainViewModel.emitEvent(
                    MainUiEventPayload.LoadUrl(
                        twinteUrlBuilder(serverSettings).appendPath("course").appendPath(it).buildUrl(),
                    ),
                )
            }
    }

    // SubWebViewでMainWebViewに読み込ませたくなった時に呼び出される
    override fun subWebViewCallback(url: String) {
        mainViewModel.clearPageLoadError()
        mainViewModel.emitEvent(MainUiEventPayload.LoadUrl(url))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleMainMessageAction(action: MainUiMessageAction?) {
        when (action) {
            MainUiMessageAction.OpenNotificationSettings -> {
                startActivity(
                    Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra(Settings.EXTRA_APP_PACKAGE, packageName),
                )
            }
            null -> Unit
        }
    }

    private fun handleMainUiEvent(event: MainUiEventPayload) {
        when (event) {
            is MainUiEventPayload.LoadUrl -> {
                mainViewModel.clearPageLoadError()
                mainWebViewController.onMainFrameLoadRequested()
                mainWebView?.loadUrl(event.url)
            }
            is MainUiEventPayload.OpenExternalUrl -> {
                startActivity(
                    Intent(Intent.ACTION_VIEW).apply {
                        data = event.url.toUri()
                    },
                )
            }
            is MainUiEventPayload.OpenSettings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            is MainUiEventPayload.OpenSubWebView -> {
                SubWebViewFragment.open(event.url, supportFragmentManager)
            }
            is MainUiEventPayload.Share -> {
                mainWebView?.shareScreen(event.body)
            }
        }
    }
}
