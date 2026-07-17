package com.example.meterly.ui.components.analyticsScreen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meterly.model.Payment
import com.example.meterly.ui.components.analyticsScreen.waterScreen2.BottomSectionWaterScreen2
import com.example.meterly.ui.components.analyticsScreen.waterScreen2.MiddleSectionWaterScreen2
import com.example.meterly.ui.components.analyticsScreen.waterScreen2.TopSectionWaterScreen2
import com.example.meterly.ui.theme.secondaryGradient
import com.patrykandpatrick.vico.compose.cartesian.axis.Axis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries

@Composable
@Preview
fun WaterScreen2(
    payment: Payment? = null,
    allPayments: List<Payment> = emptyList(),
    selectedMonth: Int = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1,
    selectedYear: Int = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR),
    onPeriodSelected: (month: Int, year: Int) -> Unit = { _, _ -> },
    onLeftArrowWater2: () -> Unit = {},
    onRightArrowWater2: () -> Unit = {},
    startAxisWater: Axis<Axis.Position.Vertical.Start>? = null,
    bottomAxisWater: Axis<Axis.Position.Horizontal.Bottom>? = null
){
    val modelProducer = remember { CartesianChartModelProducer() }

    val sortedPayments = remember(allPayments) {
        allPayments.sortedWith(compareBy({ it.year }, { it.month }))
    }

    LaunchedEffect(sortedPayments) {
        if (sortedPayments.isEmpty()) return@LaunchedEffect
        modelProducer.runTransaction {
            lineSeries {
                series(sortedPayments.map { it.month }, sortedPayments.map { it.consumption })
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient(MaterialTheme.colorScheme))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopSectionWaterScreen2(
                onLeftArrowWater2 = onLeftArrowWater2,
                onRightArrowWater2 = onRightArrowWater2,
                selectedMonth = selectedMonth,
                selectedYear = selectedYear,
                onPeriodSelected = onPeriodSelected
            )

            if (payment != null) {
                Spacer(modifier = Modifier.height(16.dp))

                MiddleSectionWaterScreen2(
                    payment = payment,
                    allPayments = allPayments,
                    startAxisWater = { startAxisWater },
                    bottomAxisWater = { bottomAxisWater },
                    modelProducerWater = modelProducer
                )

                Spacer(modifier = Modifier.height(24.dp))

                BottomSectionWaterScreen2(
                    payment = payment,
                    allPayments = allPayments
                )
            } else {
                Spacer(modifier = Modifier.height(32.dp))
                NoDataFallback()
            }
        }
    }
}
