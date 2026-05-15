package br.com.hellodev.moviestreaming.ui.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.systemdesign.components.button.PrimaryButtonUI
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.ui.components.welcome.WelcomeSlideUI

@Composable
fun WelcomeScreen(
    navigateToHomeAuthScreen: () -> Unit
) {
    WelcomeContent(
        navigateToHomeAuthScreen = navigateToHomeAuthScreen
    )
}

@Composable
fun WelcomeContent(navigateToHomeAuthScreen: () -> Unit) {
    val slideItems = listOf(
        Pair(
            first = stringResource(R.string.welcome_title_1),
            second = stringResource(R.string.welcome_description_1),
        ),
        Pair(
            first = stringResource(R.string.welcome_title_2),
            second = stringResource(R.string.welcome_description_2),
        ),
        Pair(
            first = stringResource(R.string.welcome_title_3),
            second = stringResource(R.string.welcome_description_3),
        )
    )
    val pagerState = rememberPagerState {
        slideItems.size
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                    .paint(
                        painter = painterResource(id = R.drawable.placeholder_welcome),
                        contentScale = ContentScale.Crop
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background_gradient),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(bottom = 24.dp)
                ) {
                    WelcomeSlideUI(
                        modifier = Modifier
                            .weight(1f),
                        slideItems = slideItems,
                        pagerState = pagerState
                    )

                    PrimaryButtonUI(
                        text = stringResource(R.string.get_started),
                        onClick = { navigateToHomeAuthScreen() },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun WelcomePreview() {
    MovieStreamingTheme {
        WelcomeContent(
            navigateToHomeAuthScreen = {  }
        )
    }
}