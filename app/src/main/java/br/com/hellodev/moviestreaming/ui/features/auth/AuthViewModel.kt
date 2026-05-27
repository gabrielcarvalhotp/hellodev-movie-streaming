package br.com.hellodev.moviestreaming.ui.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.extentions.isValidEmail
import br.com.hellodev.moviestreaming.core.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.input.InputType
import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.domain.models.User
import br.com.hellodev.moviestreaming.domain.usecases.SaveUserUseCase
import br.com.hellodev.moviestreaming.domain.usecases.SignInUseCase
import br.com.hellodev.moviestreaming.domain.usecases.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(AuthState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000L),
            initialValue = AuthState()
        )

    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.OnEmailChanged -> {
                onEmailChanged(value = action.value)
            }

            is AuthAction.OnPasswordChanged -> {
                onPasswordChanged(value = action.value)
            }

            is AuthAction.OnPasswordVisibilityChanged -> {
                onPasswordVisibilityChanged()
            }

            is AuthAction.OnSignIn -> {
                onSignIn()
            }

            is AuthAction.OnSignUp -> {
                onSignUp()
            }

            AuthAction.ClearFeedback -> {
                clearFeedback()
            }
        }
    }

    private fun onEmailChanged(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value, emailError = null)
        }
    }

    private fun onPasswordChanged(value: String) {
        _state.update { currentState ->
            currentState.copy(password = value, passwordError = null)
        }
    }

    private fun onPasswordVisibilityChanged() {
        _state.update { currentState ->
            currentState.copy(passwordVisibility = !currentState.passwordVisibility)
        }
    }

    private fun onSignIn() {
        if (!validateInputValues()) return

        viewModelScope.launch {
            _state.update { currentState -> currentState.copy(isLoading = true) }

            try {
                signInUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )

                sendFeedback(FeedbackType.SUCCESS, R.string.sign_in_with_success)
                _state.update { currentState -> currentState.copy(isAuthenticated = true) }
            } catch (e: Exception) {
                sendFeedback(FeedbackType.ERROR, FirebaseService.validError(e.message))
            } finally {
                _state.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }

    private fun onSignUp() {
        if (!validateInputValues()) return

        viewModelScope.launch {
            _state.update { currentState -> currentState.copy(isLoading = true) }

            try {
                signUpUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )

                saveUserUseCase(user = User(email = _state.value.email))

                sendFeedback(FeedbackType.SUCCESS, R.string.user_created_with_success)
                _state.update { currentState -> currentState.copy(isAuthenticated = true) }
            } catch (e: Exception) {
                sendFeedback(FeedbackType.ERROR, FirebaseService.validError(e.message))
            } finally {
                _state.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }

    private fun validateInputValues(): Boolean {
        var isValid = true
        _state.update { currentState ->
            currentState.copy(
                emailError = if (!currentState.email.isValidEmail()) {
                    isValid = false
                    R.string.error_email_invalid
                } else null,
                passwordError = if (!currentState.password.isNotEmpty()) {
                    isValid = false
                    R.string.error_password_invalid
                } else null
            )
        }
        return isValid
    }

    private fun sendFeedback(feedbackType: FeedbackType, message: Int) {
        _state.update { currentState ->
            currentState.copy(
                hasFeedback = true,
                feedbackUI = Pair(feedbackType, message)
            )
        }
    }

    private fun clearFeedback() {
        _state.update { currentState ->
            currentState.copy(
                hasFeedback = false,
                feedbackUI = null
            )
        }
    }
}