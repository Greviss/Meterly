package com.example.meterly.ui.components.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.R
import com.example.meterly.model.Payment
import com.example.meterly.model.UtilityType

@Composable
fun BottomSectionHomeScreen(
    currentPayments: Map<UtilityType, Payment?>
){
    val gas = currentPayments[UtilityType.GAS]
    val water = currentPayments[UtilityType.WATER]
    val light = currentPayments[UtilityType.LIGHT]
    val sewerage = currentPayments[UtilityType.SEWERAGE]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp)
    ) {
        CardUtil(
            icon = painterResource(R.drawable.icon_gas),
            title = "Газ",
            subTitle = formatAmount(gas?.amountDue),
            cardColor = badgeBgColor(gas),
            textColor = badgeTextColor(gas),
            colorBg = Color(0xFFC2DEF5),
            payment = paymentStatus(gas)
        )

        Spacer(modifier = Modifier.height(4.dp))

        CardUtil(
            icon = painterResource(R.drawable.icon_water),
            title = "Вода",
            subTitle = formatAmount(water?.amountDue),
            cardColor = badgeBgColor(water),
            textColor = badgeTextColor(water),
            colorBg = Color(0xFFC1E9EE),
            payment = paymentStatus(water)
        )

        Spacer(modifier = Modifier.height(4.dp))

        CardUtil(
            icon = painterResource(R.drawable.icon_light),
            title = "Світло",
            subTitle = formatAmount(light?.amountDue),
            cardColor = badgeBgColor(light),
            textColor = badgeTextColor(light),
            colorBg = Color(0xFFECE7C1),
            payment = paymentStatus(light)
        )

        Spacer(modifier = Modifier.height(4.dp))

        CardUtil(
            icon = painterResource(R.drawable.icon_sewerage),
            title = "Канал.",
            subTitle = formatAmount(sewerage?.amountDue),
            cardColor = badgeBgColor(sewerage),
            textColor = badgeTextColor(sewerage),
            colorBg = Color(0xFFC1EED2),
            payment = paymentStatus(sewerage)
        )
    }
}

private fun formatAmount(value: Double?): String {
    return if (value != null) {
        String.format("%.2f", value) + " грн."
    } else {
        "0 грн."
    }
}

private fun paymentStatus(payment: Payment?): String {
    return when {
        payment == null -> "Немає даних"
        payment.isPaid -> "Сплачено"
        else -> "Не оплачено"
    }
}

@Composable
private fun badgeBgColor(payment: Payment?): Color {
    return if (payment?.isPaid == true) {
        Color(0xFF4CAF50)
    } else {
        MaterialTheme.colorScheme.errorContainer
    }
}

@Composable
private fun badgeTextColor(payment: Payment?): Color {
    return if (payment?.isPaid == true) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onErrorContainer
    }
}

@Composable
fun CardUtil(
    icon: Painter,
    title: String,
    subTitle: String,
    cardColor: Color,
    textColor: Color,
    colorBg: Color,
    payment: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 6.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = colorBg)) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "До сплати: ",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = subTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
            Surface(
                color = cardColor,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = payment,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}