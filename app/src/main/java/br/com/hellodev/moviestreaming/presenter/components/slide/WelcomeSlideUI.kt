package br.com.hellodev.moviestreaming.presenter.components.slide

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.indicator.SlideIndicatorUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeSlideUI(
    modifier: Modifier = Modifier,
    slideItems: List<Pair<String, String>>,
    pagerState: PagerState
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxSize()
                .weight(1f),
            pageContent = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = slideItems[pagerState.currentPage].first,
                        style = MovieStreamingTheme.typography.title.copy(
                            color = MovieStreamingTheme.colorScheme.whiteColor
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = slideItems[pagerState.currentPage].second,
                        style = MovieStreamingTheme.typography.body.copy(
                            color = MovieStreamingTheme.colorScheme.whiteColor,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        )

        SlideIndicatorUI(
            modifier = Modifier
                .padding(bottom = 24.dp),
            totalIndicators = slideItems.size,
            currentIndicator = pagerState.currentPage
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun WelcomeSlideUIPreview() {
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

    WelcomeSlideUI(
        slideItems = slideItems,
        pagerState = pagerState
    )
}