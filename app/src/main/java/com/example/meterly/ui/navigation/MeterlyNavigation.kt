package com.example.meterly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meterly.ui.screens.authentication.EnterScreen
import com.example.meterly.ui.screens.authentication.RegisterScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.AnalyticsScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.HomeScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.PaymentsScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.ProfileScreen
import com.example.meterly.ui.screens.splashScreen.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Registration : Screen("registration_screen")
    object Enter : Screen("enter_screen")
    object Home: Screen("home_screen")
    object Payments: Screen("payments_screen")
    object Analytics: Screen("analytics_screen")
    object Profile: Screen("profile_screen")

}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // ========== SPLASH ==========
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
                onRegisterClick = { name, address, phone ->
                    println("🎯 Реєстрація: $name, $address, $phone")

                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Registration.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Screen.Enter.route)
                }
            )
        }

        composable(Screen.Enter.route) {
            EnterScreen(
                onSignIn = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Analytics.route) {
            AnalyticsScreen(navController = navController)
        }

        composable(Screen.Payments.route) {
            PaymentsScreen(navController = navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}