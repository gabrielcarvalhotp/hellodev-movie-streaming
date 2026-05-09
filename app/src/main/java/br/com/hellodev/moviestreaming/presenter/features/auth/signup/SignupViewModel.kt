package br.com.hellodev.moviestreaming.presenter.features.auth.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnEmailChanged -> { onEmailChanged(value = action.value) }
            is SignupAction.OnPasswordChanged -> { onPasswordChanged(value = action.value) }
            is SignupAction.OnPasswordVisibilityChanged -> { onPasswordVisibilityChanged() }
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
}