package com.example.meterly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.meterly.ui.navigation.AppNavigation
import com.example.meterly.ui.theme.MeterlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MeterlyTheme {
                val navController = rememberNavController()

                AppNavigation(navController = navController)
            }
        }
    }
}