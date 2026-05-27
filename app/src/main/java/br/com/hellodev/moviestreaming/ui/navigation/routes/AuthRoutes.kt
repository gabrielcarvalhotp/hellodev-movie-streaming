package br.com.hellodev.moviestreaming.ui.navigation.routes

import kotlinx.serialization.Serializable

sealed class AuthRoutes {
    @Serializable
    data object Graph : AuthRoutes()
    @Serializable
    data object Home : AuthRoutes()
    @Serializable
    data object SignIn: AuthRoutes()
    @Serializable
    data object SignUp: AuthRoutes()
}