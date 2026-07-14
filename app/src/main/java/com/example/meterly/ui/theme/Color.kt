package com.example.meterly.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val PrimaryBlue = Color(0xFF4A9FE7)
val LightGray = Color(0xFFECECEC)
val Gray = Color(0xFFD5D5D5)
val White = Color(0xFFFFFFFF)
val LightBlue = Color(0xFFBFE9F4)
val Black = Color(0xFF000000)

fun primaryGradient(colors: ColorScheme) = Brush.verticalGradient(
    listOf(
        colors.primary,
        colors.secondary,
        colors.background
    )
)
fun secondaryGradient(colors: ColorScheme) = Brush.verticalGradient(
    listOf(
        colors.primary,
        colors.secondary,
        colors.background
    )
)

val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    background = Color(0xFFF5F7FA),
    surface = Color.White,
    secondary = Color(0xFF4A90D9),
    tertiary = Color(0xFF6FCF97),
    error = Color(0xFFD78786),
    onBackground = Color.Black,
    onSurface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White
)

val DarkColors = darkColorScheme(
    primary = PrimaryBlue,
    background = Color(0xFF1A1A1A),
    surface = Color(0xFF1E1E1E),
    secondary = Color(0xFF4A90D9),
    tertiary = Color(0xFF7DD4A1),
    error = Color(0xFFD78786),
    onBackground = Color.White,
    onSurface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.White
)