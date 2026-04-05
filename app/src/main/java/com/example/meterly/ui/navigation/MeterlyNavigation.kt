package com.example.meterly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.GasScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.LightScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.SewerageScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.WaterScreen
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
    object GasScreenPay: Screen("gas_screen_pay")
    object WaterScreenPay: Screen("water_screen_pay")
    object LightScreenPay: Screen("light_screen_pay")
    object SewerageScreenPay: Screen("sewerage_screen_pay")
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

        composable(Screen.GasScreenPay.route){
            GasScreen(
                onLeftArrowGas = {
                    navController.navigate(Screen.SewerageScreenPay.route)
                },
                onRightArrowGas = {
                    navController.navigate(Screen.WaterScreenPay.route)
                }
            )
        }
        composable(Screen.WaterScreenPay.route){
            WaterScreen(
                onLeftArrowWater = {
                    navController.navigate(Screen.GasScreenPay.route)
                },
                onRightArrowWater = {
                    navController.navigate(Screen.LightScreenPay.route)
                },
            )
        }
        composable(Screen.LightScreenPay.route){
            LightScreen(
                onLeftArrowLight = {
                    navController.navigate(Screen.WaterScreenPay.route)
                },
                onRightArrowLight = {
                    navController.navigate(Screen.SewerageScreenPay.route)
                }
            )
        }
        composable(Screen.SewerageScreenPay.route){
            SewerageScreen(
                onLeftArrowSewerage = {
                    navController.navigate(Screen.LightScreenPay.route)
                },
                onRightArrowSewerage = {
                    navController.navigate(Screen.GasScreenPay.route)
                }
            )
        }
    }
}