package br.com.hellodev.moviestreaming.features.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.extentions.isValidEmail
import br.com.hellodev.moviestreaming.core.input.InputType
import br.com.hellodev.moviestreaming.features.signup.domain.usecases.SignupUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUseCase: SignupUseCase
) : ViewModel() {
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
                viewModelScope.launch {
                    onSignup()
                }
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

    private suspend fun onSignup() {
        if (!validateInputValues()) return

        signupUseCase(
            email = _state.value.email,
            password = _state.value.password
        )
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