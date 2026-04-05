package com.example.meterly.ui.screens.bottomNavMenuComponents

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.GasScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.LightScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.SewerageScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.WaterScreen
import kotlinx.coroutines.launch

@Composable
fun PaymentsScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> GasScreen(
                onLeftArrowGas = { scope.launch { pagerState.animateScrollToPage(3) } },
                onRightArrowGas = { scope.launch { pagerState.animateScrollToPage(1) } }
            )
            1 -> WaterScreen(
                onLeftArrowWater = { scope.launch { pagerState.animateScrollToPage(0) } },
                onRightArrowWater = { scope.launch { pagerState.animateScrollToPage(2) } }
            )
            2 -> LightScreen(
                onLeftArrowLight = {
                    scope.launch { pagerState.animateScrollToPage(1) }
                },
                onRightArrowLight = {
                    scope.launch { pagerState.animateScrollToPage(3) }
                }
            )
            3 -> SewerageScreen(
                onLeftArrowSewerage = {
                    scope.launch { pagerState.animateScrollToPage(2) }
                },
                onRightArrowSewerage = {
                    scope.launch { pagerState.animateScrollToPage(0) }
                }
            )
        }
    }
}