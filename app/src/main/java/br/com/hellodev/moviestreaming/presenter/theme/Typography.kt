package br.com.hellodev.moviestreaming.presenter.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R

val UrbanistFamily = FontFamily(
    Font(R.font.urbanist_400, FontWeight.Normal),
    Font(R.font.urbanist_500, FontWeight.Medium),
    Font(R.font.urbanist_600, FontWeight.SemiBold),
    Font(R.font.urbanist_700, FontWeight.Bold)
)

val AppTypography = MyTypography(

    title =  TextStyle(
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
        fontFamily = UrbanistFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    ),

    body = TextStyle(
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
        fontFamily = UrbanistFamily,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Right,
        letterSpacing = 0.2.sp,
    ),

    button = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
        fontFamily = UrbanistFamily,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        letterSpacing = 0.2.sp
    ),

    label = TextStyle(
        lineHeight = 19.6.sp,
        fontFamily = UrbanistFamily,
        textAlign = TextAlign.Right,
        letterSpacing = 0.2.sp
    ),

    link = TextStyle(
        fontFamily = UrbanistFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.2.sp
    )
)