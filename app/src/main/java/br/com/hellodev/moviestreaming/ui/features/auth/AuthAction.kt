package br.com.hellodev.moviestreaming.ui.features.auth

sealed interface AuthAction {
    data class OnEmailChanged(val value: String) : AuthAction
    data object ClearFeedback: AuthAction
    data class OnPasswordChanged(val value: String) : AuthAction
    data object OnPasswordVisibilityChanged : AuthAction
    data object OnSignIn: AuthAction
    data object OnSignUp: AuthAction
}