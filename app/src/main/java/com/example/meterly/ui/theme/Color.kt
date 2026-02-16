package com.example.meterly.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val PrimaryBlue = Color(0xFF00BCD4)
val LightBlue = Color(0xFF2196F3)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

fun PrimaryGradient() = Brush.verticalGradient(
    colors = listOf(PrimaryBlue, LightBlue)
)

fun SecondaryGradient() = Brush.horizontalGradient(
    colors = listOf(LightBlue, PrimaryBlue)
)
