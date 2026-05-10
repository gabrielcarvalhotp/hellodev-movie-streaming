package br.com.hellodev.moviestreaming.presenter.features.auth.signup

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.core.extentions.isValidEmail
import br.com.hellodev.moviestreaming.core.input.InputType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnEmailChanged -> {
                onEmailChanged(value = action.value)
            }

            is SignupAction.OnPasswordChanged -> {
                onPasswordChanged(value = action.value)
            }

            is SignupAction.OnPasswordVisibilityChanged -> {
                onPasswordVisibilityChanged()
            }

            is SignupAction.OnSignup -> {
                onSignup()
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
}