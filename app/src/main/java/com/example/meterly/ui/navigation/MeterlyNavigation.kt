package com.example.meterly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meterly.ui.screens.authentication.EnterScreen
import com.example.meterly.ui.screens.authentication.RegisterScreen
import com.example.meterly.ui.screens.splashScreen.SplashScreen
sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Registration: Screen("registration_screen")
    object Enter: Screen("enter_screen")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onAnimationFinished = {
                    navController.navigate(Screen.Registration.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Registration.route) {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(Screen.Enter.route) {
                        popUpTo(Screen.Registration.route) { inclusive = false}
                    }
                }
            )
        }

        composable(Screen.Enter.route) {
            EnterScreen(
                onSignIn = {
                    navController.navigate(Screen.Registration.route){
                        popUpTo(Screen.Enter.route) { inclusive = false }
                    }
                }
            )
        }
    }
}