package com.example.meterly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MeterlyTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme =
            if (darkTheme)
                DarkColors
            else
                LightColors,
        content = content
    )
}