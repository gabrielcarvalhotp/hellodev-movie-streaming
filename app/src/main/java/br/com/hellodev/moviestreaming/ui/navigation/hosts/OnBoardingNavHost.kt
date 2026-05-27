package br.com.hellodev.moviestreaming.ui.navigation.hosts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.ui.navigation.routes.AuthRoutes
import br.com.hellodev.moviestreaming.ui.navigation.routes.OnBoardingRoutes
import br.com.hellodev.moviestreaming.ui.features.splash.SplashScreen
import br.com.hellodev.moviestreaming.ui.features.welcome.WelcomeScreen
import br.com.hellodev.moviestreaming.ui.navigation.routes.AppRoutes

@Composable
fun OnBoardingNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnBoardingRoutes.Splash
    ) {
        composable<OnBoardingRoutes.Splash> {
            SplashScreen(
                navigateToAppScreen = {
                    navHostController.navigate(AppRoutes.Graph) {
                        popUpTo(OnBoardingRoutes.Splash) { inclusive = true }
                    }
                },
                navigateToWelcomeScreen = {
                    navHostController.navigate(OnBoardingRoutes.Welcome) {
                        popUpTo(OnBoardingRoutes.Splash) { inclusive = true }
                    }
                },
                navigateToHomeAuthScreen = {
                    navHostController.navigate(AuthRoutes.Graph) {
                        popUpTo<OnBoardingRoutes.Splash> { inclusive = true }
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
        
        appNavHost(
            navigateToHomeAuthScreen = {
                navHostController.navigate(AuthRoutes.Graph) {
                    popUpTo<AppRoutes.Home> { inclusive = true }
                }
            }
        )
    }
}