package com.example.meterly.ui.screens.bottomNavMenuComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.GasScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.LightScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.SewerageScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.WaterScreen

@Composable
@Preview
fun PaymentsScreen(modifier: Modifier = Modifier, navController: NavController? = null){
    val screens = listOf(GasScreen(), WaterScreen(), LightScreen(), SewerageScreen())
}