package com.example.meterly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.meterly.ui.navigation.Screen
import com.example.meterly.ui.navigation.AppNavigation
import com.example.meterly.ui.navigation.BottomNavigationBar
import com.example.meterly.ui.theme.MeterlyTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeterlyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route

                    val screensWithBotNavMenu = listOf(
                        Screen.Home.route,
                        Screen.Analytics.route,
                        Screen.Payments.route,
                        Screen.Profile.route
                    )

                    Scaffold(
                        bottomBar = {
                            if (currentRoute in screensWithBotNavMenu){
                                BottomNavigationBar(navController)
                            }
                        }
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            AppNavigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
}