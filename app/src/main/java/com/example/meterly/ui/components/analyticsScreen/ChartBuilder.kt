package com.example.meterly.ui.components.analyticsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.meterly.model.Payment
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter

private val monthShortNames = listOf(
    "Січ", "Лют", "Бер", "Кві", "Тра", "Чер",
    "Лип", "Сер", "Вер", "Жов", "Лис", "Гру"
)

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
