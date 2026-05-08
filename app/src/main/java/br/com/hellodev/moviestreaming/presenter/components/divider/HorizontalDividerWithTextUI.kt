package br.com.hellodev.moviestreaming.presenter.components.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun HorizontalDividerWithTextUI(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            color = MovieStreamingTheme.colorScheme.dividerColor
        )

        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp),
            style = MovieStreamingTheme.typography.body.copy(
                color = MovieStreamingTheme.colorScheme.textColor
            )
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            color = MovieStreamingTheme.colorScheme.dividerColor
        )
    }
}

@PreviewLightDark
@Composable
private fun HorizontalDividerWithTextUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDividerWithTextUI(
                text = "ou"
            )
        }
    }
}