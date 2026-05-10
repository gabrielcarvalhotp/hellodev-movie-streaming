package br.com.hellodev.moviestreaming.presenter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = MyColorScheme(
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    primaryBackgroundColor = PrimaryBackgroundColorLight,
    secondaryBackgroundColor = SecondaryBackgroundColorLight,
    backgroundSocialButtonColor = BackgroundSocialButtonColorLight,
    textFieldBackgroundColor = TextFieldBackgroundColorLight,
    alphaDefaultColor = AlphaDefaultColor,
    topAppBarColor = TopAppBarColorLight,
    dividerColor = DividerColorLight,
    borderColor = Greyscale500Color,
    textColor = TextColorLight,
    iconColor = IconColorLight,
    secondaryButtonColor = SecondaryButtonColorLight,
    secondaryButtonTextColor = SecondaryButtonTextColorLight,
    switchInactiveBackgroundColor = SwitchInactiveBackgroundColorLight,
    switchActiveBackgroundColor = SwitchInactiveBackgroundColorLight,
    tabRowUnselectedTextColor = TabRowUnselectedTextColorLight,
    successColor = SuccessColor,
    alertColor = AlertColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    whiteColor = WhiteColor,
    blackColor = BlackColor,
    transparentColor = TransparentColor,
    spotColor = SpotColor,
    ambientColor = AmbientColor,
    placeholderColor = Greyscale500Color
)

private val DarkColorScheme = MyColorScheme(
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    primaryBackgroundColor = PrimaryBackgroundColorDark,
    secondaryBackgroundColor = SecondaryBackgroundColorDark,
    backgroundSocialButtonColor = BackgroundSocialButtonColorDark,
    textFieldBackgroundColor = TextFieldBackgroundColorDark,
    alphaDefaultColor = AlphaDefaultColor,
    topAppBarColor = TopAppBarColorDark,
    dividerColor = DividerColorDark,
    borderColor = Greyscale500Color,
    textColor = TextColorDark,
    iconColor = IconColorDark,
    secondaryButtonColor = SecondaryButtonColorDark,
    secondaryButtonTextColor = SecondaryButtonTextColorDark,
    switchInactiveBackgroundColor = SwitchInactiveBackgroundColorDark,
    switchActiveBackgroundColor = SwitchInactiveBackgroundColorDark,
    tabRowUnselectedTextColor = TabRowUnselectedTextColorDark,
    successColor = SuccessColor,
    alertColor = AlertColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    whiteColor = WhiteColor,
    blackColor = BlackColor,
    transparentColor = TransparentColor,
    spotColor = SpotColor,
    ambientColor = AmbientColor,
    placeholderColor = Greyscale500Color
)

private val LocalColorScheme = compositionLocalOf { LightColorScheme }
val LocalTypography = staticCompositionLocalOf<MyTypography> { error("No typography provided") }

object MovieStreamingTheme {
    val colorScheme: MyColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
    val typography: MyTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun MovieStreamingTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme by remember(isDarkTheme) {
        mutableStateOf(if (isDarkTheme) DarkColorScheme else LightColorScheme)
    }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides AppTypography
    ) {
        MaterialTheme(content = content)
    }
}