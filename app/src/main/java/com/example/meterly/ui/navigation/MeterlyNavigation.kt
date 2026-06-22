package com.example.meterly.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meterly.state.AuthState
import com.example.meterly.ui.components.analyticsScreen.GasScreen2
import com.example.meterly.ui.components.analyticsScreen.LightScreen2
import com.example.meterly.ui.components.analyticsScreen.SewerageScreen2
import com.example.meterly.ui.components.analyticsScreen.WaterScreen2
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.GasScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.LightScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.SewerageScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.WaterScreen
import com.example.meterly.ui.screens.authentication.EnterScreen
import com.example.meterly.ui.screens.authentication.OtpScreen
import com.example.meterly.ui.screens.authentication.RegisterScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.AnalyticsScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.HomeScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.PaymentsScreen
import com.example.meterly.ui.screens.bottomNavMenuComponents.ProfileScreen
import com.example.meterly.ui.screens.splashScreen.SplashScreen
import com.example.meterly.viewModel.AuthViewModel

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Registration : Screen("registration_screen")
    object Enter : Screen("enter_screen")
    object Otp : Screen("otp_screen")
    object Home: Screen("home_screen")
    object Payments: Screen("payments_screen")
    object GasScreenPay: Screen("gas_screen_pay")
    object WaterScreenPay: Screen("water_screen_pay")
    object LightScreenPay: Screen("light_screen_pay")
    object SewerageScreenPay: Screen("sewerage_screen_pay")
    object Analytics: Screen("analytics_screen")
    object GasScreenAnalytics: Screen("gas_screen_analytics")
    object WaterScreenAnalytics: Screen("water_screen_analytics")
    object LightScreenAnalytics: Screen("light_screen_analytics")
    object SewerageScreenAnalytics: Screen("sewerage_screen_analytics")
    object Profile: Screen("profile_screen")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
            var splashAnimationDone by remember { mutableStateOf(false) }

            SplashScreen(
                onAnimationFinished = {
                    splashAnimationDone = true
                }
            )

            LaunchedEffect(isLoggedIn, splashAnimationDone) {
                if (isLoggedIn != null && splashAnimationDone) {
                    val destination = if (isLoggedIn == true) Screen.Home.route else Screen.Registration.route
                    navController.navigate(destination) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            }
        }

        composable(Screen.Registration.route) {
            val context = LocalContext.current
            val authState by authViewModel.authState.collectAsState()
            val registerError by authViewModel.registerError.collectAsState()

            RegisterScreen(
                onRegisterClick = { name, address, phone ->
                    authViewModel.register(
                        fullName = name,
                        address = address,
                        phone = phone,
                        activity = context as Activity
                    )
                },
                onLoginClick = {
                    navController.navigate(Screen.Enter.route)
                },
                errorMessage = registerError
            )

            LaunchedEffect(authState) {
                if (authState is AuthState.CodeSent) {
                    navController.navigate(Screen.Otp.route)
                }
            }
        }

        composable(Screen.Enter.route) {
            val context = LocalContext.current
            val authState by authViewModel.authState.collectAsState()
            var loginErrorMessage by remember {
                mutableStateOf<String?>(null)
            }

            EnterScreen(
                onEnterClick = { address, phone ->
                    loginErrorMessage = null
                    authViewModel.login(
                        address = address,
                        phone = phone,
                        activity = context as Activity,
                        onUserNotFound = {
                            loginErrorMessage = "Користувача з такою адресою та номером телефону не знайдено"
                        }
                    )
                },
                onSignIn = {
                    navController.popBackStack()
                },
                errorMessage = loginErrorMessage
            )

            LaunchedEffect(authState) {
                if (authState is AuthState.CodeSent) {
                    navController.navigate(Screen.Otp.route)
                }
            }
        }

        composable(Screen.Otp.route) {
            val authState by authViewModel.authState.collectAsState()
            OtpScreen(
                authState = authState,
                onCodeEntered = { code ->
                    authViewModel.submitCode(code)
                },
                onVerified = {
                    authViewModel.onVerificationSuccess()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
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
            ProfileScreen(
                navController = navController,
                onSignOutClick = {
                    authViewModel.signOut()
                    navController.navigate(Screen.Registration.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.GasScreenPay.route){
            GasScreen(
                onLeftArrowGas = { navController.navigate(Screen.SewerageScreenPay.route) },
                onRightArrowGas = { navController.navigate(Screen.WaterScreenPay.route) }
            )
        }
        composable(Screen.WaterScreenPay.route){
            WaterScreen(
                onLeftArrowWater = { navController.navigate(Screen.GasScreenPay.route) },
                onRightArrowWater = { navController.navigate(Screen.LightScreenPay.route) },
            )
        }
        composable(Screen.LightScreenPay.route){
            LightScreen(
                onLeftArrowLight = { navController.navigate(Screen.WaterScreenPay.route) },
                onRightArrowLight = { navController.navigate(Screen.SewerageScreenPay.route) }
            )
        }
        composable(Screen.SewerageScreenPay.route){
            SewerageScreen(
                onLeftArrowSewerage = { navController.navigate(Screen.LightScreenPay.route) },
                onRightArrowSewerage = { navController.navigate(Screen.GasScreenPay.route) }
            )
        }
        composable(Screen.GasScreenAnalytics.route){
            GasScreen2(
                onLeftArrowGas2 = { navController.navigate(Screen.SewerageScreenAnalytics.route) },
                onRightArrowGas2 = { navController.navigate(Screen.WaterScreenAnalytics.route) }
            )
        }
        composable(Screen.WaterScreenAnalytics.route){
            WaterScreen2(
                onLeftArrowWater2 = { navController.navigate(Screen.GasScreenAnalytics.route) },
                onRightArrowWater2 = { navController.navigate(Screen.LightScreenAnalytics.route) }
            )
        }
        composable(Screen.LightScreenAnalytics.route){
            LightScreen2(
                onLeftArrowLight2 = { navController.navigate(Screen.WaterScreenAnalytics.route) },
                onRightArrowLight2 = { navController.navigate(Screen.SewerageScreenAnalytics.route) }
            )
        }
        composable(Screen.SewerageScreenAnalytics.route){
            SewerageScreen2(
                onLeftArrowSewerage2 = { navController.navigate(Screen.LightScreenAnalytics.route) },
                onRightArrowSewerage2 = { navController.navigate(Screen.GasScreenAnalytics.route) }
            )
        }
    }
}