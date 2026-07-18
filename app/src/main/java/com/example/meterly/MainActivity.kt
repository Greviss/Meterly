package com.example.meterly

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.meterly.model.ThemeMode
import com.example.meterly.ui.navigation.Screen
import com.example.meterly.ui.navigation.AppNavigation
import com.example.meterly.ui.navigation.BottomNavigationBar
import com.example.meterly.ui.theme.MeterlyTheme
import com.example.meterly.viewModel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()

        Log.d("MainActivity", "onCreate: notification channel created")

        setContent {
            val navController = rememberNavController()

            val themeViewModel: ThemeViewModel = viewModel()
            val theme by themeViewModel.theme.collectAsState()

            val darkTheme = when (theme) {
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
                ThemeMode.DARK -> true
                ThemeMode.LIGHT -> false
            }

            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

            val screensWithBottomBar = listOf(
                Screen.Home.route,
                Screen.Analytics.route,
                Screen.Payments.route,
                Screen.Profile.route,
                Screen.GasScreenPay.route,
                Screen.WaterScreenPay.route,
                Screen.LightScreenPay.route,
                Screen.SewerageScreenPay.route
            )

            MeterlyTheme(
                darkTheme = darkTheme
            ) {
                Scaffold(
                    bottomBar = {
                        if (currentRoute in screensWithBottomBar) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),

                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(navController)
                    }
                }
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "meterly_reminders",
                "Нагадування",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Щомісячні нагадування про показники"
                enableVibration(true)
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
            Log.d("MainActivity", "createNotificationChannel: channel created")
        }
    }
}