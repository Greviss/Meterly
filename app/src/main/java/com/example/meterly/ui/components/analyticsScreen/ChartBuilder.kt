package com.example.meterly.ui.components.analyticsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.meterly.model.Payment
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries

private val monthShortNames = listOf(
    "Січ", "Лют", "Бер", "Кві", "Тра", "Чер",
    "Лип", "Сер", "Вер", "Жов", "Лис", "Гру"
)

@Composable
fun rememberPaymentChartModelProducer(
    payments: List<Payment>
): CartesianChartModelProducer {
    val modelProducer = remember { CartesianChartModelProducer() }

    val sorted = remember(payments) {
        payments.sortedWith(compareBy({ it.year }, { it.month }))
    }

    LaunchedEffect(sorted) {
        if (sorted.isEmpty()) return@LaunchedEffect
        modelProducer.runTransaction {
            lineSeries {
                val xValues = sorted.map { it.month }
                val yValues = sorted.map { it.consumption }
                series(xValues, yValues)
            }
        }
    }

    return modelProducer
}

@Composable
fun rememberMonthStartAxis() = VerticalAxis.rememberStart()

@Composable
fun rememberMonthBottomAxis(
    payments: List<Payment>
) = HorizontalAxis.rememberBottom(
    valueFormatter = remember(payments) {
        CartesianValueFormatter { _, value, _ ->
            val index = value.toInt() - 1
            if (index in monthShortNames.indices) {
                monthShortNames[index]
            } else ""
        }
    }
)
