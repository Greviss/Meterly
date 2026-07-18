package com.example.meterly.ui.components.paymentsScreen.paymentsScreens.sewerageScreenComp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.material3.Checkbox
import com.example.meterly.model.Payment
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.gasScreenComp.ColumnElem
import com.example.meterly.util.AmountFormatter
import kotlin.math.roundToInt

@Composable
fun BottomSectionSewerageScreen(
    currentPayment: Payment?,
    previousPayment: Payment?,
    roundAmounts: Boolean = false,
    onPaidChange: (Boolean) -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Відомості",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (currentPayment == null) {
                    Text(
                        text = "Розрахунок ще не виконано.",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    ColumnElem(
                        titleSewerage = "Витрачено",
                        subtitleSewerage = "В цьому місяці",
                        iconSewerage = Icons.Default.Plumbing,
                        iconBgColorSewerage = Color(0xFFFFBFBD),
                        iconTintSewerage = Color(0xFFF44336),
                        valueSewerage = "${formatValue(currentPayment.consumption)} м³",
                        textColorSewerage = Color(0xFFF44336),
                        cardColorSewerage = Color(0xFFDC8E89),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ColumnElem(
                        titleSewerage = "Витрачено",
                        subtitleSewerage = "В попередньому місяці",
                        iconSewerage = Icons.Default.Plumbing,
                        iconBgColorSewerage = Color(0xFFFFEFC1),
                        iconTintSewerage = Color(0xFFFF9800),
                        valueSewerage = if (previousPayment != null) "${formatValue(previousPayment.consumption)} м³" else "-",
                        textColorSewerage = Color(0xFFFF9800),
                        cardColorSewerage = Color(0xFFFFD797),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ColumnElem(
                        titleSewerage = "До сплати",
                        subtitleSewerage = "В цьому місяці",
                        iconSewerage = Icons.Default.Money,
                        iconBgColorSewerage = Color(0xFFE2FFBE),
                        iconTintSewerage = Color(0xFF4CAF50),
                        valueSewerage = formatAmountValue(currentPayment.amountDue, roundAmounts),
                        textColorSewerage = Color(0xFF4CAF50),
                        cardColorSewerage = Color(0xFFB6DB8B),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ColumnElem(
                        titleSewerage = "До сплати",
                        subtitleSewerage = "В попередньому місяці",
                        iconSewerage = Icons.Default.Money,
                        iconBgColorSewerage = Color(0xFF4CAF50),
                        iconTintSewerage = Color(0xFFE2FFBE),
                        valueSewerage = if (previousPayment != null) formatAmountValue(previousPayment.amountDue, roundAmounts) else "-",
                        textColorSewerage = Color(0xFFB6DB8B),
                        cardColorSewerage = Color(0xFF4CAF50),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ColumnElem(
                        titleSewerage = "Тариф",
                        subtitleSewerage = "Актуальна ціна тарифа",
                        iconSewerage = Icons.Default.AutoGraph,
                        iconBgColorSewerage = Color(0xFFBBE0FF),
                        iconTintSewerage = Color(0xFF3F51B5),
                        valueSewerage = formatRateValue(currentPayment.rate, "м³", roundAmounts),
                        textColorSewerage = Color(0xFF3F51B5),
                        cardColorSewerage = Color(0xFF87B4D7),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Сплачено",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Checkbox(
                            checked = currentPayment.isPaid,
                            onCheckedChange = onPaidChange
                        )
                    }
                }
            }
        }
    }
}

private fun formatValue(value: Double): String {
    return if (value == value.toLong().toDouble()) {
        value.toLong().toString()
    } else {
        String.format("%.2f", value)
    }
}

private fun formatAmountValue(value: Double, round: Boolean): String {
    return if (round) {
        "${value.toInt()} грн."
    } else {
        formatValue(value) + " грн."
    }
}

private fun formatRateValue(value: Double, unit: String, round: Boolean): String {
    val formatted = if (round) value.toInt().toString() else formatValue(value)
    return "$formatted грн./$unit"
}

@Composable
fun ColumnElem(titleSewerage: String,
               subtitleSewerage: String,
               iconSewerage: ImageVector, iconBgColorSewerage: Color, iconTintSewerage: Color,
               valueSewerage: String,
               textColorSewerage: Color,
               cardColorSewerage: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(iconBgColorSewerage, CircleShape)
            ) {
                Icon(
                    imageVector = iconSewerage,
                    contentDescription = null,
                    tint = iconTintSewerage,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titleSewerage,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = subtitleSewerage,
                    fontSize = 12.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColorSewerage
                )
            ) {
                Text(
                    text = valueSewerage,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorSewerage,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

