package br.com.hellodev.moviestreaming.navigation.hosts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.navigation.routes.AuthRoutes
import br.com.hellodev.moviestreaming.navigation.routes.OnBoardingRoutes
import br.com.hellodev.moviestreaming.ui.features.splash.SplashScreen
import br.com.hellodev.moviestreaming.ui.features.welcome.WelcomeScreen

@Composable
fun OnBoardingNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnBoardingRoutes.Splash
    ) {
        composable<OnBoardingRoutes.Splash> {
            SplashScreen(
                navigateToWelcomeScreen = {
                    navHostController.navigate(OnBoardingRoutes.Welcome) {
                        popUpTo(OnBoardingRoutes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<OnBoardingRoutes.Welcome> {
            WelcomeScreen(
                navigateToHomeAuthScreen = {
                    navHostController.navigate(AuthRoutes.Graph) {
                        popUpTo<OnBoardingRoutes.Welcome> { inclusive = true }
                    }
                }
            )
        }

        authNavHost(navHostController)
    }
}