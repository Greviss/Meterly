package com.example.meterly.ui.components.bottomNavComponents

sealed class Screen(val route: String){
    object Home: Screen("home_screen")
    object Payments: Screen("payments_screen")
    object Analytics: Screen("analytics_screen")
    object Profile: Screen("profile_screen")
}