package br.com.hellodev.moviestreaming.ui.navigation.routes

import kotlinx.serialization.Serializable


sealed class AppRoutes {
    @Serializable
    data object Graph : AppRoutes()
    @Serializable
    data object Home: AppRoutes()
}
