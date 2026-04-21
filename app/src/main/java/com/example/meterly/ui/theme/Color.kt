package com.example.meterly.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val PrimaryBlue = Color(0xFF55D0E2)
val LightGray = Color(0xFFEAEAEA)
val Gray = Color(0xFFD4D4D4)
val LightBlue = Color(0xFF4AA5EF)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

fun primaryGradient() = Brush.verticalGradient(
    colors = listOf(PrimaryBlue, LightBlue)
)
fun secondaryGradient() = Brush.verticalGradient(
    colors = listOf(White, PrimaryBlue)
)

fun whiteButtonGradient() = Brush.verticalGradient(
    colors = listOf(LightGray, Gray)
)
