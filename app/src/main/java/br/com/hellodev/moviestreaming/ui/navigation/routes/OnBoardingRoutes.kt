package br.com.hellodev.moviestreaming.ui.navigation.routes

import kotlinx.serialization.Serializable

sealed class OnBoardingRoutes {
    @Serializable
    data object Graph : OnBoardingRoutes()

    @Serializable
    data object Splash : OnBoardingRoutes()

    @Serializable
    data object Welcome : OnBoardingRoutes()
}