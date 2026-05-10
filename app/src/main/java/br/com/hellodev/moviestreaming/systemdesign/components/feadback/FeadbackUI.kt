package br.com.hellodev.moviestreaming.systemdesign.components.feadback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.feedback.FeedbackType
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme

@Composable
fun FeedbackUI(
    modifier: Modifier = Modifier,
    message: String,
    type: FeedbackType
) {
    val containerColor = when (type) {
        FeedbackType.SUCCESS -> MovieStreamingTheme.colorScheme.successColor
        FeedbackType.INFO -> MovieStreamingTheme.colorScheme.infoColor
        FeedbackType.WARNING -> MovieStreamingTheme.colorScheme.warningColor
        FeedbackType.ERROR -> MovieStreamingTheme.colorScheme.alertColor
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = message,
                tint = MovieStreamingTheme.colorScheme.whiteColor
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = message,
                style = MovieStreamingTheme.typography.label.copy(
                    color = MovieStreamingTheme.colorScheme.whiteColor
                )
            )
        }
    }
}

@Preview
@Composable
private fun FeedbackUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = FeedbackType.SUCCESS
            )

            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = FeedbackType.INFO
            )

            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = FeedbackType.WARNING
            )

            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = FeedbackType.ERROR
            )
        }
    }
}