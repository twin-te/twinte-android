package net.twinte.android

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

enum class MainUiMessageAction {
    OpenNotificationSettings,
}

data class MainUiMessage(
    val id: Long,
    val text: String,
    val actionLabel: String? = null,
    val action: MainUiMessageAction? = null,
)

sealed interface MainUiEventPayload {
    data class OpenExternalUrl(val url: String) : MainUiEventPayload

    data class OpenSubWebView(val url: String) : MainUiEventPayload

    data object OpenSettings : MainUiEventPayload

    data class Share(val body: String) : MainUiEventPayload

    data class LoadUrl(val url: String) : MainUiEventPayload
}

data class MainUiEvent(
    val id: Long,
    val payload: MainUiEventPayload,
)

data class MainUiState(
    val isPageLoading: Boolean = false,
    val isGoogleSignInInProgress: Boolean = false,
    val pageLoadErrorMessage: String? = null,
    val message: MainUiMessage? = null,
    val event: MainUiEvent? = null,
)

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    private var nextMessageId = 0L

    fun onPageLoadingChanged(isLoading: Boolean) {
        _uiState.update { current ->
            current.copy(
                isPageLoading = isLoading,
                pageLoadErrorMessage = if (isLoading) null else current.pageLoadErrorMessage,
            )
        }
    }

    fun onGoogleSignInStarted() {
        _uiState.update { current ->
            current.copy(isGoogleSignInInProgress = true)
        }
    }

    fun onGoogleSignInFinished() {
        _uiState.update { current ->
            current.copy(isGoogleSignInInProgress = false)
        }
    }

    fun onPageLoadError(message: String) {
        _uiState.update { current ->
            current.copy(
                isPageLoading = false,
                pageLoadErrorMessage = message,
            )
        }
    }

    fun clearPageLoadError() {
        _uiState.update { current ->
            current.copy(pageLoadErrorMessage = null)
        }
    }

    fun showMessage(
        text: String,
        actionLabel: String? = null,
        action: MainUiMessageAction? = null,
    ) {
        _uiState.update { current ->
            current.copy(
                message = MainUiMessage(
                    id = ++nextMessageId,
                    text = text,
                    actionLabel = actionLabel,
                    action = action,
                ),
            )
        }
    }

    fun clearMessage(messageId: Long) {
        _uiState.update { current ->
            if (current.message?.id != messageId) {
                current
            } else {
                current.copy(message = null)
            }
        }
    }

    fun emitEvent(payload: MainUiEventPayload) {
        _uiState.update { current ->
            current.copy(
                event = MainUiEvent(
                    id = ++nextMessageId,
                    payload = payload,
                ),
            )
        }
    }

    fun clearEvent(eventId: Long) {
        _uiState.update { current ->
            if (current.event?.id != eventId) {
                current
            } else {
                current.copy(event = null)
            }
        }
    }
}
