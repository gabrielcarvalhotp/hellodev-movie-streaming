package br.com.hellodev.moviestreaming.ui.features.signin

sealed interface SignInAction {
    data class OnEmailChanged(val value: String) : SignInAction
    data object ClearFeedback: SignInAction
    data class OnPasswordChanged(val value: String) : SignInAction
    data object OnPasswordVisibilityChanged : SignInAction
    data object OnSignIn: SignInAction
}