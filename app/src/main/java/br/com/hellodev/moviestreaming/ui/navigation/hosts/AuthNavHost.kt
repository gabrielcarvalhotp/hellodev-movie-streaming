package br.com.hellodev.moviestreaming.ui.navigation.hosts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.ui.navigation.routes.AuthRoutes
import br.com.hellodev.moviestreaming.ui.features.auth.HomeAuthScreen
import br.com.hellodev.moviestreaming.ui.features.auth.SignInScreen
import br.com.hellodev.moviestreaming.ui.features.auth.SignUpScreen
import br.com.hellodev.moviestreaming.ui.navigation.routes.AppRoutes

fun NavGraphBuilder.authNavHost(navHostController: NavHostController) {
    navigation<AuthRoutes.Graph>(
        startDestination = AuthRoutes.Home
    ) {
        composable<AuthRoutes.Home> {
            HomeAuthScreen(
                navigateToSignInScreen = {
                    navHostController.navigate(AuthRoutes.SignIn)
                },
                navigateToSignUpScreen = {
                    navHostController.navigate(AuthRoutes.SignUp)
                }
            )
        }
        composable<AuthRoutes.SignIn> {
            SignInScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                navigateToSignUpScreen = {
                    navHostController.navigate(AuthRoutes.SignUp){
                        popUpTo(AuthRoutes.SignIn) { inclusive = true }
                    }
                },
                navigateToHomeScreen = {
                    navHostController.navigate(AppRoutes.Home){
                        popUpTo(AuthRoutes.Graph) { inclusive = true }
                    }
                }
            )
        }
        composable<AuthRoutes.SignUp> {
            SignUpScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                navigateToSignInScreen = {
                    navHostController.navigate(AuthRoutes.SignIn){
                        popUpTo(AuthRoutes.SignUp) { inclusive = true }
                    }
                },
                navigateToHomeScreen = {
                    navHostController.navigate(AppRoutes.Home){
                        popUpTo(AuthRoutes.Graph) { inclusive = true }
                    }
                }
            )
        }
    }
}