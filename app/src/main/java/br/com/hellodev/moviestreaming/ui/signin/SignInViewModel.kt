package br.com.hellodev.moviestreaming.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.extentions.isValidEmail
import br.com.hellodev.moviestreaming.core.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.input.InputType
import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.domain.usecases.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SignInState())
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
            initialValue = SignInState()
        )

    fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.OnEmailChanged -> {
                onEmailChanged(value = action.value)
            }

            is SignInAction.OnPasswordChanged -> {
                onPasswordChanged(value = action.value)
            }

            is SignInAction.OnPasswordVisibilityChanged -> {
                onPasswordVisibilityChanged()
            }

            is SignInAction.OnSignIn -> {
                onSignIn()
            }

            SignInAction.ClearFeedback -> {
                clearFeedback()
            }
        }
    }

    private fun onEmailChanged(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value)
        }
    }

    private fun onPasswordChanged(value: String) {
        _state.update { currentState ->
            currentState.copy(password = value)
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

                sendFeedback(FeedbackType.SUCCESS, R.string.user_created_with_success)
            } catch (e: Exception) {
                sendFeedback(FeedbackType.ERROR, FirebaseService.validError(e.message))
            } finally {
                _state.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }

    private fun validateInputValues(): Boolean {
        clearValidationInput()

        val emailIsValid = _state.value.email.isValidEmail()
        if (!emailIsValid) {
            _state.update { currentState ->
                currentState.copy(invalidInputType = InputType.EMAIL)
            }
            return false
        }
        val passwordIsValid = _state.value.password.isNotEmpty()
        if (!passwordIsValid) {
            _state.update { currentState ->
                currentState.copy(invalidInputType = InputType.PASSWORD)
            }
            return false
        }

        return true
    }

    private fun clearValidationInput() {
        _state.update { currentState ->
            currentState.copy(
                invalidInputType = null
            )
        }
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