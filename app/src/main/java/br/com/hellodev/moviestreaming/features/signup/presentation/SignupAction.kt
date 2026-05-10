package br.com.hellodev.moviestreaming.features.signup.presentation

sealed class SignupAction {
    data class OnEmailChanged(val value: String) : SignupAction()
    data class OnPasswordChanged(val value: String) : SignupAction()
    data object OnPasswordVisibilityChanged : SignupAction()
    data object OnSignup: SignupAction()
    data object ClearFeedback: SignupAction()
}