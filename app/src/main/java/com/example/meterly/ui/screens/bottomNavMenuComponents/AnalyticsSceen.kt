package com.example.meterly.ui.screens.bottomNavMenuComponents

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.meterly.ui.components.analyticsScreen.GasScreen2
import com.example.meterly.ui.components.analyticsScreen.LightScreen2
import com.example.meterly.ui.components.analyticsScreen.SewerageScreen2
import com.example.meterly.ui.components.analyticsScreen.WaterScreen2
import kotlinx.coroutines.launch

@Composable
fun AnalyticsScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        when (page){
            0 -> GasScreen2(
                onLeftArrowGas2 = { scope.launch { pagerState.animateScrollToPage(3) } },
                onRightArrowGas2 = { scope.launch { pagerState.animateScrollToPage(1) } }
            )

            1 -> WaterScreen2(
                onLeftArrowWater2 = { scope.launch { pagerState.animateScrollToPage(0) } },
                onRightArrowWater2 = { scope.launch { pagerState.animateScrollToPage(2) } }
            )

            2 -> LightScreen2(
                onLeftArrowLight2 = { scope.launch { pagerState.animateScrollToPage(1) } },
                onRightArrowLight2 = { scope.launch { pagerState.animateScrollToPage(3) } }
            )

            3 -> SewerageScreen2(
                onLeftArrowSewerage2 = { scope.launch { pagerState.animateScrollToPage(2) } },
                onRightArrowSewerage2 = { scope.launch { pagerState.animateScrollToPage(0) } }
            )
        }
    }
}