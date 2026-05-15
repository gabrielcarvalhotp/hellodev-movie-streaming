package br.com.hellodev.moviestreaming.navigation.routes

sealed class OnBoardingRoutes {
    @kotlinx.serialization.Serializable
    data object Graph : OnBoardingRoutes()

    @kotlinx.serialization.Serializable
    data object Splash : OnBoardingRoutes()

    @kotlinx.serialization.Serializable
    data object Welcome : OnBoardingRoutes()
}