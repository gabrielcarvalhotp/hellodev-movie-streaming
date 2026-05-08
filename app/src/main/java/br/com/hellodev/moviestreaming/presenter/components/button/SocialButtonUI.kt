package br.com.hellodev.moviestreaming.presenter.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.loading.BouncingDotsLoadingUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun SocialButtonUI(
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(58.dp)
            .border(
                width = 1.dp,
                color = MovieStreamingTheme.colorScheme.borderColor,
                shape = RoundedCornerShape(16.dp)
            ),
        enabled = !isLoading,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MovieStreamingTheme.colorScheme.backgroundSocialButtonColor,
        )
    ) {
        if (isLoading) {
            BouncingDotsLoadingUI()
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = icon,
                    contentDescription = text,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.Unspecified
                )

                text?.let {
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = text,
                        style = MovieStreamingTheme.typography.button.copy(
                            color = MovieStreamingTheme.colorScheme.textColor
                        )
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SocialButtonUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            SocialButtonUI(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Continuar com o Google",
                icon = painterResource(id = R.drawable.ic_google),
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            SocialButtonUI(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Continuar com o Facebook",
                icon = painterResource(id = R.drawable.ic_facebook),
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            SocialButtonUI(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Continuar com o Github",
                icon = painterResource(id = R.drawable.ic_github),
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SocialButtonUI(
                    icon = painterResource(id = R.drawable.ic_google),
                    isLoading = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.width(20.dp))

                SocialButtonUI(
                    icon = painterResource(id = R.drawable.ic_facebook),
                    isLoading = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.width(20.dp))

                SocialButtonUI(
                    icon = painterResource(id = R.drawable.ic_github),
                    isLoading = false,
                    onClick = {}
                )
            }
        }
    }
}