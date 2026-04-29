package com.example.meterly.ui.components.analyticsScreen.lightScreen2

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
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.WaterDrop
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
fun BottomSectionLightScreen2(){
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
                RowElementLight(titleLight1 = "Економія (грн.)",
                    subtitleLight1 = "Заощаджено",
                    valueLight1 = "0 грн.",
                    iconLight1 = Icons.Default.Eco, iconTintLight1 = Color(0xFF4CAF50), iconBgColorLight1 = Color(0xFFE8F5E9),
                    modifierLight1 = Modifier.weight(1f)
                )

                RowElementLight(titleLight1 = "Економія (%)",
                    subtitleLight1 = "Заощаджено",
                    valueLight1 = "0 %-",
                    iconLight1 = Icons.Default.Percent, iconTintLight1 = Color(0xFF4CAF50), iconBgColorLight1 = Color(0xFFE8F5E9),
                    modifierLight1 = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ){
                RowElementLight(titleLight1 = "Витрачено (кВт)",
                    subtitleLight1 = "Щоденні витрати",
                    valueLight1 = "2,1 кВт",
                    iconLight1 = Icons.Default.EnergySavingsLeaf, iconTintLight1 = Color(0xFF447EAC), iconBgColorLight1 = Color(0xFFC9D0FF),
                    modifierLight1 = Modifier.weight(1f)
                )

                RowElementLight(titleLight1 = "Витрачено (грн.)",
                    subtitleLight1 = "Щоденні витрати",
                    valueLight1 = "13,11 грн.",
                    iconLight1 = Icons.Default.Payments, iconTintLight1 = Color(0xFF9E8744), iconBgColorLight1 = Color(0xFFFFF0D7),
                    modifierLight1 = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                ColumnElementLight(
                    titleLight2 = "Макс. витрат",
                    subtitleLight2 = "За грудень 2025",
                    valueLight2 = "198 грн.",
                    iconLight2 = Icons.Default.ArrowCircleUp, iconTintLight2 = Color(0xFFA04B45), iconBgColorLight2 = Color(0xFFFFD7D5),
                    cardColorLight2 = Color(0xFFFFD7D5),
                    textColorLight2 = Color(0xFFF44336)
                )

                ColumnElementLight(
                    titleLight2 = "Мін. витрат",
                    subtitleLight2 = "За липень 2025",
                    valueLight2 = "584 грн.",
                    iconLight2 = Icons.Default.ArrowCircleDown, iconTintLight2 = Color(0xFF47954A), iconBgColorLight2 = Color(0xFFF1FFDE),
                    cardColorLight2 = Color(0xFFF1FFDE),
                    textColorLight2 = Color(0xFF4CAF50)
                )

                ColumnElementLight(
                    titleLight2 = "За увесь рік",
                    subtitleLight2 = "За 2025",
                    valueLight2 = "4789 грн.",
                    iconLight2 = Icons.Default.Wallet, iconTintLight2 = Color(0xFF67489E), iconBgColorLight2 = Color(0xFFF3E1FF),
                    cardColorLight2 = Color(0xFFF3E1FF),
                    textColorLight2 = Color(0xFF673AB7)
                )
            }
        }
    }
}

@Composable
fun RowElementLight(titleLight1: String,
               subtitleLight1: String,
               valueLight1: String,
               iconLight1: ImageVector, iconTintLight1: Color, iconBgColorLight1: Color,
               modifierLight1: Modifier = Modifier){
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
                        .background(iconBgColorLight1, CircleShape)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = iconLight1,
                            contentDescription = null,
                            tint = iconTintLight1,
                            modifier = modifierLight1.size(24.dp)
                        )
                    }
                }

                Text(text = valueLight1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titleLight1,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = subtitleLight1,
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun ColumnElementLight(
    titleLight2: String,
    subtitleLight2: String,
    valueLight2: String,
    iconLight2: ImageVector, iconTintLight2: Color, iconBgColorLight2: Color,
    cardColorLight2: Color, textColorLight2: Color
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
                    .background(iconBgColorLight2, CircleShape)
            ) {
                Icon(
                    imageVector = iconLight2,
                    contentDescription = null,
                    tint = iconTintLight2,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titleLight2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1C1E)
                )
                Text(
                    text = subtitleLight2,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColorLight2
                )
            ) {
                Text(
                    text = valueLight2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorLight2,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}