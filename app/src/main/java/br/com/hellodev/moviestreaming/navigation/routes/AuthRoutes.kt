package br.com.hellodev.moviestreaming.navigation.routes

sealed class AuthRoutes {
    @kotlinx.serialization.Serializable
    data object Graph : AuthRoutes()
    @kotlinx.serialization.Serializable
    data object Home : AuthRoutes()
    @kotlinx.serialization.Serializable
    data object SignIn: AuthRoutes()
    @kotlinx.serialization.Serializable
    data object SignUp: AuthRoutes()
}