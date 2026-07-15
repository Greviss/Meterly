package com.example.meterly.ui.components.paymentsScreen.paymentsScreens

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meterly.model.UtilityType
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.waterScreenComp.BottomSectionWaterScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.waterScreenComp.MiddleSectionWaterScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.waterScreenComp.TopSectionWaterScreen
import com.example.meterly.ui.theme.secondaryGradient
import com.example.meterly.viewModel.PaymentViewModel

@Composable
fun WaterScreen(onLeftArrowWater: () -> Unit = {},
                onRightArrowWater: () -> Unit = {}) {
    val activity = LocalActivity.current as? ComponentActivity ?: throw IllegalStateException("LocalActivity is not a ComponentActivity")

    val paymentViewModel: PaymentViewModel = viewModel(viewModelStoreOwner = activity)
    val currentPayments by paymentViewModel.currentPayments.collectAsState()
    val previousPayments by paymentViewModel.previousPayments.collectAsState()

    val currentPayment = currentPayments[UtilityType.WATER]
    val previousPayment = previousPayments[UtilityType.WATER]

    var monthBegin2 by remember { mutableStateOf("") }
    var monthEnd2 by remember { mutableStateOf("") }
    var rate2 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient(MaterialTheme.colorScheme))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSectionWaterScreen(onLeftArrowWater = onLeftArrowWater,
                onRightArrowWater = onRightArrowWater)

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionWaterScreen(
                monthBegin2 = monthBegin2,
                onMonthBeginChange2 = { monthBegin2 = it },
                monthEnd2 = monthEnd2,
                onMonthEndChange2 = { monthEnd2 = it },
                rate2 = rate2,
                onRateEndChange2 = { rate2 = it },
                paymentViewModel = paymentViewModel
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSectionWaterScreen(
                currentPayment = currentPayment,
                previousPayment = previousPayment,
                onPaidChange = { isPaid -> paymentViewModel.togglePaid(UtilityType.WATER, isPaid) }
            )
        }
    }
}