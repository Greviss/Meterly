package com.example.meterly.ui.components.bottomNavComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import com.example.meterly.ui.navigation.Screen

val navigationItems = listOf(
    NavigationItem(
        title = "Головна",
        icon = Icons.Default.Home,
        route = Screen.Home.route
    ),
    NavigationItem(
        title = "Платежі",
        icon = Icons.Default.Payments,
        route = Screen.Payments.route,
    ),
    NavigationItem(
        title = "Аналітика",
        icon = Icons.Default.Analytics,
        route = Screen.Analytics.route,
    ),
    NavigationItem(
        title = "Профіль",
        icon = Icons.Default.Person,
        route = Screen.Profile.route
    )
)