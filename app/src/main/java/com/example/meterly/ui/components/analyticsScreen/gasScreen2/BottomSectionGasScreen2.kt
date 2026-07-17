package com.example.meterly.ui.components.analyticsScreen.gasScreen2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.model.Payment
import java.util.Calendar

private val monthNames = listOf(
    "Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
    "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"
)

@Composable
fun BottomSectionGasScreen2(
    payment: Payment? = null,
    allPayments: List<Payment> = emptyList()
) {
    val amountDue = payment?.amountDue ?: 0.0
    val consumption = payment?.consumption ?: 0.0
    val selectedYear = payment?.year ?: Calendar.getInstance().get(Calendar.YEAR)
    val selectedMonth = payment?.month ?: 1

    val nonZeroPayments = allPayments.filter { it.amountDue > 0 }

    val previousPayment = allPayments
        .filter { it.date < (payment?.date ?: Long.MAX_VALUE) }
        .sortedByDescending { it.date }
        .firstOrNull()

    val prevAmount = previousPayment?.amountDue ?: 0.0
    val savings = maxOf(0.0, prevAmount - amountDue)
    val savingsPercent = if (prevAmount > 0) (savings / prevAmount * 100) else 0.0

    val daysInMonth = java.util.Calendar.getInstance().apply {
        set(selectedYear, selectedMonth - 1, 1)
    }.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
    val dailyConsumption = if (daysInMonth > 0) consumption / daysInMonth else 0.0
    val dailyCost = if (daysInMonth > 0) amountDue / daysInMonth else 0.0

    val maxPayment = nonZeroPayments.maxByOrNull { it.amountDue }
    val minPayment = nonZeroPayments.filter { it.amountDue > 0 }.minByOrNull { it.amountDue }

    val yearTotal = allPayments
        .filter { it.year == selectedYear }
        .sumOf { it.amountDue }

    fun formatMonthYear(p: Payment?): String {
        if (p == null) return "—"
        val mName = monthNames.getOrElse(p.month - 1) { "?" }
        return "$mName ${p.year}"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Відомості",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                RowElementGas(titleGas1 = "Економія (грн.)",
                    subtitleGas1 = "Заощаджено",
                    valueGas1 = "${String.format("%.2f", savings)} грн.",
                    iconGas1 = Icons.Default.Eco, iconTintGas1 = Color(0xFF4CAF50), iconBgColorGas1 = Color(0xFFE8F5E9),
                    modifierGas1 = Modifier.weight(1f)
                )

                RowElementGas(titleGas1 = "Економія (%)",
                    subtitleGas1 = "Заощаджено",
                    valueGas1 = "${String.format("%.1f", savingsPercent)} %",
                    iconGas1 = Icons.Default.Percent, iconTintGas1 = Color(0xFF4CAF50), iconBgColorGas1 = Color(0xFFE8F5E9),
                    modifierGas1 = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ){
                RowElementGas(titleGas1 = "Витрачено (м³)",
                    subtitleGas1 = "Щоденні витрати",
                    valueGas1 = "${String.format("%.2f", dailyConsumption)} м³",
                    iconGas1 = Icons.Default.LocalFireDepartment, iconTintGas1 = Color(0xFF447EAC), iconBgColorGas1 = Color(0xFFC9D0FF),
                    modifierGas1 = Modifier.weight(1f)
                )

                RowElementGas(titleGas1 = "Витрачено (грн.)",
                    subtitleGas1 = "Щоденні витрати",
                    valueGas1 = "${String.format("%.2f", dailyCost)} грн.",
                    iconGas1 = Icons.Default.Payments, iconTintGas1 = Color(0xFF9E8744), iconBgColorGas1 = Color(0xFFFFF0D7),
                    modifierGas1 = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                ColumnElementGas(
                    titleGas2 = "Макс. витрат",
                    subtitleGas2 = "За ${formatMonthYear(maxPayment)}",
                    valueGas2 = "${String.format("%.0f", maxPayment?.amountDue ?: 0.0)} грн.",
                    iconGas2 = Icons.Default.ArrowCircleUp, iconTintGas2 = Color(0xFFA04B45), iconBgColorGas2 = Color(0xFFFFD7D5),
                    cardColorGas2 = Color(0xFFFFD7D5),
                    textColorGas2 = Color(0xFFF44336)
                )

                Spacer(modifier = Modifier.height(2.dp))

                ColumnElementGas(
                    titleGas2 = "Мін. витрат",
                    subtitleGas2 = "За ${formatMonthYear(minPayment)}",
                    valueGas2 = "${String.format("%.0f", minPayment?.amountDue ?: 0.0)} грн.",
                    iconGas2 = Icons.Default.ArrowCircleDown, iconTintGas2 = Color(0xFF47954A), iconBgColorGas2 = Color(0xFFF1FFDE),
                    cardColorGas2 = Color(0xFFF1FFDE),
                    textColorGas2 = Color(0xFF4CAF50)
                )

                Spacer(modifier = Modifier.height(2.dp))

                ColumnElementGas(
                    titleGas2 = "За увесь рік",
                    subtitleGas2 = "За $selectedYear",
                    valueGas2 = "${String.format("%.0f", yearTotal)} грн.",
                    iconGas2 = Icons.Default.Wallet, iconTintGas2 = Color(0xFF67489E), iconBgColorGas2 = Color(0xFFF3E1FF),
                    cardColorGas2 = Color(0xFFF3E1FF),
                    textColorGas2 = Color(0xFF673AB7)
                )
            }
        }
    }
}

@Composable
fun RowElementGas(titleGas1: String,
                  subtitleGas1: String,
                  valueGas1: String,
                  iconGas1: ImageVector, iconTintGas1: Color, iconBgColorGas1: Color,
                  modifierGas1: Modifier = Modifier){
    Card(
        modifier = Modifier.size(width = 150.dp, height = 125.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(iconBgColorGas1, CircleShape)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = iconGas1,
                            contentDescription = null,
                            tint = iconTintGas1,
                            modifier = modifierGas1.size(24.dp)
                        )
                    }
                }

                Text(text = valueGas1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titleGas1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = subtitleGas1,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun ColumnElementGas(
    titleGas2: String,
    subtitleGas2: String,
    valueGas2: String,
    iconGas2: ImageVector, iconTintGas2: Color, iconBgColorGas2: Color,
    cardColorGas2: Color, textColorGas2: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(iconBgColorGas2, CircleShape)
            ) {
                Icon(
                    imageVector = iconGas2,
                    contentDescription = null,
                    tint = iconTintGas2,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titleGas2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = subtitleGas2,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColorGas2
                )
            ) {
                Text(
                    text = valueGas2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorGas2,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}