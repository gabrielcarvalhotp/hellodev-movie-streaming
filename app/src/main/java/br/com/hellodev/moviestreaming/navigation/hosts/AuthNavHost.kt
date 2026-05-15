package br.com.hellodev.moviestreaming.navigation.hosts

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.navigation.routes.AuthRoutes
import br.com.hellodev.moviestreaming.ui.features.homeauth.HomeAuthScreen
import br.com.hellodev.moviestreaming.ui.features.signin.SignInScreen
import br.com.hellodev.moviestreaming.ui.features.signup.SignUpScreen

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
            )
        }
    }
}