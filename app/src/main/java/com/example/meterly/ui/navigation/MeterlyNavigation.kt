package com.example.meterly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meterly.ui.screens.splashScreen.SplashScreen

enum class Screen {
    SPLASH,
    AUTH_LOGIN,
    HOME
}

@Composable
fun MeterlyNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH.name
    ) {
        composable(route = Screen.SPLASH.name) {
            SplashScreen(
                onNavigateToNextScreen = {
                    navController.navigate(Screen.AUTH_LOGIN.name) {
                        popUpTo(Screen.SPLASH.name) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.AUTH_LOGIN.name) {
            // Поки пусто
        }

        composable(route = Screen.HOME.name) {
            // Поки пусто
        }
    }
}