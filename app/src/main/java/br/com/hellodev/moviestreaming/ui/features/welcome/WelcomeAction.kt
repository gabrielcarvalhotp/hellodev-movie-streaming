package br.com.hellodev.moviestreaming.ui.features.welcome

sealed interface WelcomeAction {
    data object OnNextScreen : WelcomeAction
}