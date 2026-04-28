package com.example.meterly.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val PrimaryBlue = Color(0xFF4CA1AF)
val LightGray = Color(0xFFEFEFEF)
val Gray = Color(0xFFD5D5D5)
val Blue = Color(0xFF2196F3)
val White = Color(0xFFFFFFFF)
val LightBlue = Color(0xFFC4E0E5)
val Black = Color(0xFF000000)

fun primaryGradient() = Brush.verticalGradient(
    colors = listOf(PrimaryBlue, Blue)
)
fun secondaryGradient() = Brush.verticalGradient(
    colors = listOf(LightBlue, PrimaryBlue)
)

fun whiteButtonGradient() = Brush.verticalGradient(
    colors = listOf(LightGray, Gray)
)
