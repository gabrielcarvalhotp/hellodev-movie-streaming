package br.com.hellodev.moviestreaming.ui.navigation.hosts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.ui.features.home.HomeScreen
import br.com.hellodev.moviestreaming.ui.navigation.routes.AppRoutes

fun NavGraphBuilder.appNavHost(
    navigateToHomeAuthScreen: () -> Unit
) {
    navigation<AppRoutes.Graph>(
        startDestination = AppRoutes.Home
    ) {
        composable<AppRoutes.Home> {
            HomeScreen(
                onBackPressed = navigateToHomeAuthScreen
            )
        }
    }
}