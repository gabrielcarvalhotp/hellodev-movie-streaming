package br.com.hellodev.moviestreaming.ui.features.homeauth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.systemdesign.components.button.PrimaryButtonUI
import br.com.hellodev.moviestreaming.systemdesign.components.button.SocialButtonUI
import br.com.hellodev.moviestreaming.systemdesign.components.divider.HorizontalDividerWithTextUI
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme

@Composable
fun HomeAuthScreen(
    navigateToSignInScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
) {
    HomeAuthContent(
        navigateToSignInScreen,
        navigateToSignUpScreen
    )
}

@Composable
fun HomeAuthContent(
    navigateToSignInScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,

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
            Image(
                painter = painterResource(R.drawable.placeholder_home_authentication),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.welcome),
                style = MovieStreamingTheme.typography.title.copy(
                    color = MovieStreamingTheme.colorScheme.textColor
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            SocialButtonUI(
                icon = painterResource(R.drawable.ic_facebook),
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.label_sign_with_facebook_authentication_screen)
            )

            Spacer(modifier = Modifier.height(24.dp))

            SocialButtonUI(
                icon = painterResource(R.drawable.ic_google),
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.label_sign_with_google_authentication_screen)
            )

            Spacer(modifier = Modifier.height(24.dp))

            SocialButtonUI(
                icon = painterResource(R.drawable.ic_github),
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.label_sign_with_github_authentication_screen)
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDividerWithTextUI(
                text = stringResource(R.string.label_or_authentication_screen)
            )

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButtonUI(
                text = stringResource(R.string.label_sign_with_password_authentication_screen),
                onClick = { navigateToSignInScreen() }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.label_sign_up_account_authentication_screen),
                    modifier = Modifier
                        .clickable { navigateToSignUpScreen() },
                    style = MovieStreamingTheme.typography.label.copy(
                        color = MovieStreamingTheme.colorScheme.textColor
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.label_sign_up_authentication_screen),
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {  navigateToSignUpScreen() },
                    style = MovieStreamingTheme.typography.label.copy(
                        color = MovieStreamingTheme.colorScheme.defaultColor,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }

        }
    }
}

@PreviewLightDark
@Composable
private fun HomeAuthScreenPreview() {
    MovieStreamingTheme{
        HomeAuthContent(
            navigateToSignInScreen = { },
            navigateToSignUpScreen = { }
        )
    }
}