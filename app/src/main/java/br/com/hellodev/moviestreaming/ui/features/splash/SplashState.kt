package br.com.hellodev.moviestreaming.ui.features.splash

data class SplashState(
    val isWelcomeVisited: Boolean = false,
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = false
)