package com.example.meterly.ui.screens.bottomNavMenuComponents

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.meterly.model.UtilityType
import com.example.meterly.ui.components.analyticsScreen.GasScreen2
import com.example.meterly.ui.components.analyticsScreen.LightScreen2
import com.example.meterly.ui.components.analyticsScreen.SewerageScreen2
import com.example.meterly.ui.components.analyticsScreen.WaterScreen2
import com.example.meterly.viewModel.PaymentViewModel
import kotlinx.coroutines.launch

@Composable
fun AnalyticsScreen(navController: NavHostController) {
    val paymentViewModel: PaymentViewModel = viewModel()
    val analyticsPayments by paymentViewModel.analyticsPayments.collectAsState()
    val allPaymentsByType by paymentViewModel.allPaymentsByType.collectAsState()
    val selectedMonth by paymentViewModel.selectedAnalyticsMonth.collectAsState()
    val selectedYear by paymentViewModel.selectedAnalyticsYear.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 4 })
    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        when (page){
            0 -> GasScreen2(
                payment = analyticsPayments[UtilityType.GAS],
                allPayments = allPaymentsByType[UtilityType.GAS] ?: emptyList(),
                selectedMonth = selectedMonth,
                selectedYear = selectedYear,
                onPeriodSelected = { month, year ->
                    paymentViewModel.selectAnalyticsPeriod(month, year)
                },
                onLeftArrowGas2 = { scope.launch { pagerState.animateScrollToPage(3) } },
                onRightArrowGas2 = { scope.launch { pagerState.animateScrollToPage(1) } }
            )

            1 -> WaterScreen2(
                payment = analyticsPayments[UtilityType.WATER],
                allPayments = allPaymentsByType[UtilityType.WATER] ?: emptyList(),
                selectedMonth = selectedMonth,
                selectedYear = selectedYear,
                onPeriodSelected = { month, year ->
                    paymentViewModel.selectAnalyticsPeriod(month, year)
                },
                onLeftArrowWater2 = { scope.launch { pagerState.animateScrollToPage(0) } },
                onRightArrowWater2 = { scope.launch { pagerState.animateScrollToPage(2) } }
            )

            2 -> LightScreen2(
                payment = analyticsPayments[UtilityType.LIGHT],
                allPayments = allPaymentsByType[UtilityType.LIGHT] ?: emptyList(),
                selectedMonth = selectedMonth,
                selectedYear = selectedYear,
                onPeriodSelected = { month, year ->
                    paymentViewModel.selectAnalyticsPeriod(month, year)
                },
                onLeftArrowLight2 = { scope.launch { pagerState.animateScrollToPage(1) } },
                onRightArrowLight2 = { scope.launch { pagerState.animateScrollToPage(3) } }
            )

            3 -> SewerageScreen2(
                payment = analyticsPayments[UtilityType.SEWERAGE],
                allPayments = allPaymentsByType[UtilityType.SEWERAGE] ?: emptyList(),
                selectedMonth = selectedMonth,
                selectedYear = selectedYear,
                onPeriodSelected = { month, year ->
                    paymentViewModel.selectAnalyticsPeriod(month, year)
                },
                onLeftArrowSewerage2 = { scope.launch { pagerState.animateScrollToPage(2) } },
                onRightArrowSewerage2 = { scope.launch { pagerState.animateScrollToPage(0) } }
            )
        }
    }
}