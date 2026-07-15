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
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.sewerageScreenComp.BottomSectionSewerageScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.sewerageScreenComp.MiddleSectionSewerageScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.sewerageScreenComp.TopSectionSewerageScreen
import com.example.meterly.ui.theme.secondaryGradient
import com.example.meterly.viewModel.PaymentViewModel

@Composable
fun SewerageScreen(onLeftArrowSewerage: () -> Unit = {},
                   onRightArrowSewerage: () -> Unit = {}) {
    val activity = LocalActivity.current as? ComponentActivity ?: throw IllegalStateException("LocalActivity is not a ComponentActivity")

    val paymentViewModel: PaymentViewModel = viewModel(viewModelStoreOwner = activity)
    val currentPayments by paymentViewModel.currentPayments.collectAsState()
    val previousPayments by paymentViewModel.previousPayments.collectAsState()

    val currentPayment = currentPayments[UtilityType.SEWERAGE]
    val previousPayment = previousPayments[UtilityType.SEWERAGE]

    var monthBegin4 by remember { mutableStateOf("") }
    var monthEnd4 by remember { mutableStateOf("") }
    var rate4 by remember { mutableStateOf("") }

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
            TopSectionSewerageScreen(onLeftArrowSewerage = onLeftArrowSewerage,
                onRightArrowSewerage = onRightArrowSewerage)

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionSewerageScreen(
                monthBegin4 = monthBegin4,
                onMonthBeginChange4 = { monthBegin4 = it },
                monthEnd4 = monthEnd4,
                onMonthEndChange4 = { monthEnd4 = it },
                rate4 = rate4,
                onRateEndChange4 = { rate4 = it },
                paymentViewModel = paymentViewModel
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSectionSewerageScreen(
                currentPayment = currentPayment,
                previousPayment = previousPayment,
                onPaidChange = { isPaid -> paymentViewModel.togglePaid(UtilityType.SEWERAGE, isPaid) }
            )
        }
    }
}