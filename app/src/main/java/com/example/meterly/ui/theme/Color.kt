package com.example.meterly.ui.theme

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

fun primaryGradient() = Brush.verticalGradient(
    colors = listOf(Color(0xFF1E4D8F),
        Color(0xFF3678C9),
        Color(0xFFB6D8F7))
)
fun secondaryGradient() = Brush.verticalGradient(
    colors = listOf(Color(0xFF4A90D9),
        Color(0xFF87C0E8),
        Color(0xFFEBF9FF)
    )
)

fun whiteButtonGradient() = Brush.verticalGradient(
    colors = listOf(LightGray, Gray)
)
