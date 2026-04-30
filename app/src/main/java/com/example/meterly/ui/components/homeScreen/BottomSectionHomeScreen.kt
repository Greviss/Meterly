package com.example.meterly.ui.components.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun BottomSectionHomeScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        CardUtil(icon = painterResource(R.drawable.icon_gas),
            title = "Газ",
            subTitle = "0 грн.",
            cardColor = Color(0xFFEC8078),
            colorBg = Color(0xFF89C3F1),
            payment = "Не оплачено")

        Spacer(modifier = Modifier.height(4.dp))

        CardUtil(icon = painterResource(R.drawable.icon_water),
            title = "Вода",
            subTitle = "0 грн.",
            cardColor = Color(0xFFEC8078),
            colorBg = Color(0xFF74C9D4),
            payment = "Не оплачено")

        Spacer(modifier = Modifier.height(4.dp))

        CardUtil(icon = painterResource(R.drawable.icon_light),
            title = "Світло",
            subTitle = "0 грн.",
            cardColor = Color(0xFFEC8078),
            colorBg = Color(0xFFEEE384),
            payment = "Не оплачено")

        Spacer(modifier = Modifier.height(4.dp))

        CardUtil(icon = painterResource(R.drawable.icon_sewerage),
            title = "Канал.",
            subTitle = "0 грн.",
            cardColor = Color(0xFFEC8078),
            colorBg = Color(0xFF589788),
            payment = "Не оплачено")
    }
}

@Composable
fun CardUtil(
    icon: Painter,
    title: String,
    subTitle: String,
    cardColor: Color,
    colorBg: Color,
    payment: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
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
                    color = Color.Black
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "До сплати: ",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = subTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF4CAF50)
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
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}