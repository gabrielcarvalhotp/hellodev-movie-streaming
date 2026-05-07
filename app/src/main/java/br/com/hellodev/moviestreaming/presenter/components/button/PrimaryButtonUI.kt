package br.com.hellodev.moviestreaming.presenter.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.loading.BouncingDotsLoadingUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun PrimaryButtonUI(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 24.dp,
                shape = CircleShape,
                spotColor = MovieStreamingTheme.colorScheme.spotColor,
                ambientColor = MovieStreamingTheme.colorScheme.ambientColor
            )
            .height(58.dp),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = MovieStreamingTheme.colorScheme.defaultColor,
            disabledContainerColor = MovieStreamingTheme.colorScheme.disabledDefaultColor
        ),
    ) {
        if (isLoading) {
            BouncingDotsLoadingUI(
                modifier = modifier
                    .size(48.dp)
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight.Bold,
                        color = MovieStreamingTheme.colorScheme.whiteColor,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PrimaryButtonUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PrimaryButtonUI(
                text = "Primary Button",
                onClick = { },
                icon = painterResource(R.drawable.ic_arrow_left)
            )

            PrimaryButtonUI(
                text = "Loading Button",
                onClick = { },
                isLoading = true
            )
        }
    }
}