package com.example.meterly.ui.components.analyticsScreen.waterScreen2

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSectionWaterScreen2(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.White)
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
                color = Color.Black
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                RowElementWater(titleWater1 = "Економія (грн.)",
                    subtitleWater1 = "Заощаджено",
                    valueWater1 = "0 грн.",
                    iconWater1 = Icons.Default.Eco, iconTintWater1 = Color(0xFF4CAF50), iconBgColorWater1 = Color(0xFFE8F5E9),
                    modifierWater1 = Modifier.weight(1f)
                )

                RowElementWater(titleWater1 = "Економія (%)",
                    subtitleWater1 = "Заощаджено",
                    valueWater1 = "0 %-",
                    iconWater1 = Icons.Default.Percent, iconTintWater1 = Color(0xFF4CAF50), iconBgColorWater1 = Color(0xFFE8F5E9),
                    modifierWater1 = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ){
                RowElementWater(titleWater1 = "Витрачено (м³)",
                    subtitleWater1 = "Щоденні витрати",
                    valueWater1 = "0,26 м³",
                    iconWater1 = Icons.Default.LocalFireDepartment, iconTintWater1 = Color(0xFF447EAC), iconBgColorWater1 = Color(0xFFC9D0FF),
                    modifierWater1 = Modifier.weight(1f)
                )

                RowElementWater(titleWater1 = "Витрачено (грн.)",
                    subtitleWater1 = "Щоденні витрати",
                    valueWater1 = "3,62 грн.",
                    iconWater1 = Icons.Default.Payments, iconTintWater1 = Color(0xFF9E8744), iconBgColorWater1 = Color(0xFFFFF0D7),
                    modifierWater1 = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                ColumnElementWater(
                    titleWater2 = "Макс. витрат",
                    subtitleWater2 = "За грудень 2025",
                    valueWater2 = "496 грн.",
                    iconWater2 = Icons.Default.ArrowCircleUp, iconTintWater2 = Color(0xFFA04B45), iconBgColorWater2 = Color(0xFFFFD7D5),
                    cardColorWater2 = Color(0xFFFFD7D5),
                    textColorWater2 = Color(0xFFF44336)
                )

                ColumnElementWater(
                    titleWater2 = "Мін. витрат",
                    subtitleWater2 = "За липень 2025",
                    valueWater2 = "805 грн.",
                    iconWater2 = Icons.Default.ArrowCircleDown, iconTintWater2 = Color(0xFF47954A), iconBgColorWater2 = Color(0xFFF1FFDE),
                    cardColorWater2 = Color(0xFFF1FFDE),
                    textColorWater2 = Color(0xFF4CAF50)
                )

                ColumnElementWater(
                    titleWater2 = "За увесь рік",
                    subtitleWater2 = "За 2025",
                    valueWater2 = "6556 грн.",
                    iconWater2 = Icons.Default.Wallet, iconTintWater2 = Color(0xFF67489E), iconBgColorWater2 = Color(0xFFF3E1FF),
                    cardColorWater2 = Color(0xFFF3E1FF),
                    textColorWater2 = Color(0xFF673AB7)
                )
            }
        }
    }
}

@Composable
fun RowElementWater(titleWater1: String,
               subtitleWater1: String,
               valueWater1: String,
               iconWater1: ImageVector, iconTintWater1: Color, iconBgColorWater1: Color,
               modifierWater1: Modifier = Modifier){
    Card(
        modifier = Modifier.size(width = 150.dp, height = 125.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(iconBgColorWater1, CircleShape)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = iconWater1,
                            contentDescription = null,
                            tint = iconTintWater1,
                            modifier = modifierWater1.size(24.dp)
                        )
                    }
                }

                Text(text = valueWater1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titleWater1,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitleWater1,
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun ColumnElementWater(
    titleWater2: String,
    subtitleWater2: String,
    valueWater2: String,
    iconWater2: ImageVector, iconTintWater2: Color, iconBgColorWater2: Color,
    cardColorWater2: Color, textColorWater2: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
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
                    .background(iconBgColorWater2, CircleShape)
            ) {
                Icon(
                    imageVector = iconWater2,
                    contentDescription = null,
                    tint = iconTintWater2,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titleWater2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1C1E)
                )
                Text(
                    text = subtitleWater2,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColorWater2
                )
            ) {
                Text(
                    text = valueWater2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorWater2,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}