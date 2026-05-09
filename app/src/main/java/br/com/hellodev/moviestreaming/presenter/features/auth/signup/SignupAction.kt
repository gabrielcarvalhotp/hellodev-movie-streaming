package br.com.hellodev.moviestreaming.presenter.features.auth.signup

sealed class SignupAction {
    data class OnEmailChanged(val value: String) : SignupAction()
    data class OnPasswordChanged(val value: String) : SignupAction()
    data object OnPasswordVisibilityChanged : SignupAction()
}