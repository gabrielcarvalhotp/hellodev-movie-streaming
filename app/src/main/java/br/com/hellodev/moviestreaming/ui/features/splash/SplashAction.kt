package br.com.hellodev.moviestreaming.ui.features.splash

sealed interface SplashAction {
    data object OnNextScreen: SplashAction
}