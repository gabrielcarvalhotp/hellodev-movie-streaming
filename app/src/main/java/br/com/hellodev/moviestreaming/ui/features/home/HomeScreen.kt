package br.com.hellodev.moviestreaming.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.systemdesign.components.topappbar.TopAppBarUI
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        onAction = viewModel::onAction,
        onBackPressed = onBackPressed
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    onBackPressed: () -> Unit
) {
    LaunchedEffect(state.logoutWithSuccess) {
        if (state.logoutWithSuccess) {
            onBackPressed()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
        topBar = {
            TopAppBarUI(
                onBackPressed = { onAction(HomeAction.OnSignOut) }
            )
        }
        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Bem vindo a tela home",
                style = MovieStreamingTheme.typography.title.copy(
                    color = MovieStreamingTheme.colorScheme.defaultColor
                )
            )
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    MovieStreamingTheme {
        HomeContent(
            state = HomeState(),
            onAction = {},
            onBackPressed = { }
        )
    }
}