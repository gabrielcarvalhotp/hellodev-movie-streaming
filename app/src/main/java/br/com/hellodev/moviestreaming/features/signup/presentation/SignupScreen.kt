package br.com.hellodev.moviestreaming.features.signup.presentation

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.input.InputType
import br.com.hellodev.moviestreaming.core.input.inputErrorMessage
import br.com.hellodev.moviestreaming.systemdesign.components.button.PrimaryButtonUI
import br.com.hellodev.moviestreaming.systemdesign.components.button.SocialButtonUI
import br.com.hellodev.moviestreaming.systemdesign.components.divider.HorizontalDividerWithTextUI
import br.com.hellodev.moviestreaming.systemdesign.components.textfield.TextFieldUI
import br.com.hellodev.moviestreaming.systemdesign.components.topappbar.TopAppBarUI
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    SignupContent(
        state = state,
        onAction = viewModel::onAction,
        onBackPressed = onBackPressed,
        modifier = modifier
    )
}

@Composable
fun SignupContent(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBarUI(
                onBackPressed = onBackPressed
            )
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.label_title_signup_screen),
                style = MovieStreamingTheme.typography.title.copy(
                    color = MovieStreamingTheme.colorScheme.textColor
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldUI(
                modifier = Modifier,
                value = state.email,
                label = stringResource(id = R.string.label_input_email_signup_screen),
                placeholder = "example@gmail.com",
                isError = state.invalidInputType == InputType.EMAIL,
                error = stringResource(state.invalidInputType.inputErrorMessage()),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    onAction(SignupAction.OnEmailChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldUI(
                modifier = Modifier,
                value = state.password,
                label = stringResource(R.string.label_input_password_signup_screen),
                placeholder = "********",
                isError = state.invalidInputType == InputType.PASSWORD,
                error = stringResource(state.invalidInputType.inputErrorMessage()),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    if (state.password.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                onAction(SignupAction.OnPasswordVisibilityChanged)
                            },
                            content = {
                                Icon(
                                    painter = if (state.passwordVisibility) {
                                        painterResource(id = R.drawable.ic_hide)
                                    } else {
                                        painterResource(id = R.drawable.ic_show)
                                    },
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (state.passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                onValueChange = {
                    onAction(SignupAction.OnPasswordChanged(value = it))
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButtonUI(
                text = stringResource(id = R.string.label_button_signup_screen),
                isLoading = false,
                enabled = true,
                onClick = { onAction(SignupAction.OnSignup) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDividerWithTextUI(
                text = stringResource(id = R.string.label_or_signup_screen)
            )

            Spacer(modifier = Modifier.height(20.dp))

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

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.label_sign_in_account_signup_screen),
                    style = MovieStreamingTheme.typography.label.copy(
                        color = MovieStreamingTheme.colorScheme.textColor
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.label_sign_in_signup_screen),
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { },
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
private fun SignupPreview() {
    MovieStreamingTheme {
        SignupContent(
            state = SignupState(),
            onAction = { },
            onBackPressed = { }
        )
    }
}