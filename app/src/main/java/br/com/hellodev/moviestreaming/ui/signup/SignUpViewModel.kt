package br.com.hellodev.moviestreaming.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.extentions.isValidEmail
import br.com.hellodev.moviestreaming.core.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.input.InputType
import br.com.hellodev.moviestreaming.core.services.FirebaseService
import br.com.hellodev.moviestreaming.domain.models.User
import br.com.hellodev.moviestreaming.domain.usecases.SaveUserUseCase
import br.com.hellodev.moviestreaming.domain.usecases.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnEmailChanged -> {
                onEmailChanged(value = action.value)
            }

            is SignUpAction.OnPasswordChanged -> {
                onPasswordChanged(value = action.value)
            }

            is SignUpAction.OnPasswordVisibilityChanged -> {
                onPasswordVisibilityChanged()
            }

            is SignUpAction.OnSignup -> {
                onSignup()
            }

            SignUpAction.ClearFeedback -> {
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

    private fun onSignup() {
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