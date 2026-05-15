package br.com.hellodev.moviestreaming.ui.signup

sealed class SignUpAction {
    data class OnEmailChanged(val value: String) : SignUpAction()
    data class OnPasswordChanged(val value: String) : SignUpAction()
    data object OnPasswordVisibilityChanged : SignUpAction()
    data object OnSignup: SignUpAction()
    data object ClearFeedback: SignUpAction()
}