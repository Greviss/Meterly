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
fun BottomSectionGasScreen2() {
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
                RowElementGas(titleGas1 = "Економія (грн.)",
                    subtitleGas1 = "Заощаджено",
                    valueGas1 = "0 грн.",
                    iconGas1 = Icons.Default.Eco, iconTintGas1 = Color(0xFF4CAF50), iconBgColorGas1 = Color(0xFFE8F5E9),
                    modifierGas1 = Modifier.weight(1f)
                )

                RowElementGas(titleGas1 = "Економія (%)",
                    subtitleGas1 = "Заощаджено",
                    valueGas1 = "0 %-",
                    iconGas1 = Icons.Default.Percent, iconTintGas1 = Color(0xFF4CAF50), iconBgColorGas1 = Color(0xFFE8F5E9),
                    modifierGas1 = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ){
                RowElementGas(titleGas1 = "Витрачено (м³)",
                    subtitleGas1 = "Щоденні витрати",
                    valueGas1 = "0 м³",
                    iconGas1 = Icons.Default.LocalFireDepartment, iconTintGas1 = Color(0xFF447EAC), iconBgColorGas1 = Color(0xFFC9D0FF),
                    modifierGas1 = Modifier.weight(1f)
                )

                RowElementGas(titleGas1 = "Витрачено (грн.)",
                    subtitleGas1 = "Щоденні витрати",
                    valueGas1 = "0 грн.",
                    iconGas1 = Icons.Default.Payments, iconTintGas1 = Color(0xFF9E8744), iconBgColorGas1 = Color(0xFFFFF0D7),
                    modifierGas1 = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                ColumnElementGas(
                    titleGas2 = "Макс. витрат",
                    subtitleGas2 = "За грудень 2025",
                    valueGas2 = "1248 грн.",
                    iconGas2 = Icons.Default.ArrowCircleUp, iconTintGas2 = Color(0xFFA04B45), iconBgColorGas2 = Color(0xFFFFD7D5),
                    cardColorGas2 = Color(0xFFFFD7D5),
                    textColorGas2 = Color(0xFFF44336)
                )

                ColumnElementGas(
                    titleGas2 = "Мін. витрат",
                    subtitleGas2 = "За липень 2025",
                    valueGas2 = "225 грн.",
                    iconGas2 = Icons.Default.ArrowCircleDown, iconTintGas2 = Color(0xFF47954A), iconBgColorGas2 = Color(0xFFF1FFDE),
                    cardColorGas2 = Color(0xFFF1FFDE),
                    textColorGas2 = Color(0xFF4CAF50)
                )

                ColumnElementGas(
                    titleGas2 = "За увесь рік",
                    subtitleGas2 = "За 2025",
                    valueGas2 = "7102 грн.",
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
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titleGas1,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = subtitleGas1,
                fontSize = 10.sp,
                color = Color.Gray,
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
                    color = Color(0xFF1A1C1E)
                )
                Text(
                    text = subtitleGas2,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
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