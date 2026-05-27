package br.com.hellodev.moviestreaming.ui.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.systemdesign.components.loading.CircularLoadingUI
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel<SplashViewModel>(),
    navigateToAppScreen: () -> Unit,
    navigateToWelcomeScreen: () -> Unit,
    navigateToHomeAuthScreen: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.isLoading) {
        if (!state.isLoading) {
            if (state.isWelcomeVisited) {
                if (state.isAuthenticated) {
                    navigateToAppScreen()
                } else {
                    navigateToHomeAuthScreen()
                }
            } else {
                navigateToWelcomeScreen()
            }
        }
    }

    LaunchedEffect(true) {
        scope.launch {
            delay(3000)
            viewModel.onAction(action = SplashAction.OnNextScreen)
        }
    }

    SplashContent()
}

@Composable
fun SplashContent() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )

            CircularLoadingUI()
        }
    }
}

@PreviewLightDark
@Composable
private fun SplashPreview() {
    MovieStreamingTheme {
        SplashContent()
    }
}