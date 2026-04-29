package net.twinte.android

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MainUiState(
    val isPageLoading: Boolean = false,
    val isGoogleSignInInProgress: Boolean = false,
)

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun onPageLoadingChanged(isLoading: Boolean) {
        _uiState.update { current ->
            current.copy(isPageLoading = isLoading)
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
}
