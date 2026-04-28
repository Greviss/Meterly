package com.example.meterly.ui.components.analyticsScreen.sewerageScreen2

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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material3.HorizontalDivider
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
import com.example.meterly.ui.components.analyticsScreen.lightScreen2.ColumnElement

@Composable
fun BottomSectionSewerageScreen2(){
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                RowElementSewerage(titleSewerage1 = "Економія (грн.)",
                    subtitleSewerage1 = "Заощаджено",
                    valueSewerage1 = "0 грн.",
                    iconSewerage1 = Icons.Default.Eco, iconTintSewerage1 = Color(0xFF4CAF50), iconBgColorSewerage1 = Color(0xFFE8F5E9),
                    modifierSewerage1 = Modifier.weight(1f)
                )

                RowElementSewerage(titleSewerage1 = "Економія (%)",
                    subtitleSewerage1 = "Заощаджено",
                    valueSewerage1 = "0 %-",
                    iconSewerage1 = Icons.Default.Percent, iconTintSewerage1 = Color(0xFF4CAF50), iconBgColorSewerage1 = Color(0xFFE8F5E9),
                    modifierSewerage1 = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ){
                RowElementSewerage(titleSewerage1 = "Витрачено (кВт)",
                    subtitleSewerage1 = "Щоденні витрати",
                    valueSewerage1 = "2,1 кВт",
                    iconSewerage1 = Icons.Default.LocalFireDepartment, iconTintSewerage1 = Color(0xFF447EAC), iconBgColorSewerage1 = Color(0xFFC9D0FF),
                    modifierSewerage1 = Modifier.weight(1f)
                )

                RowElementSewerage(titleSewerage1 = "Витрачено (грн.)",
                    subtitleSewerage1 = "Щоденні витрати",
                    valueSewerage1 = "13,11 грн.",
                    iconSewerage1 = Icons.Default.Payments, iconTintSewerage1 = Color(0xFF9E8744), iconBgColorSewerage1 = Color(0xFFFFF0D7),
                    modifierSewerage1 = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                ColumnElementSewerage(
                    titleSewerage2 = "Макс. витрат",
                    subtitleSewerage2 = "За грудень 2025",
                    valueSewerage2 = "198 грн.",
                    iconSewerage2 = Icons.Default.ArrowCircleUp,
                    iconTintSewerage2 = Color(0xFFA04B45),
                    iconBgColorSewerage2 = Color(0xFFFFD7D5),
                    cardColorSewerage2 = Color(0xFFFFD7D5),
                    textColorSewerage2 = Color(0xFFF44336)
                )

                ColumnElementSewerage(
                    titleSewerage2 = "Мін. витрат",
                    subtitleSewerage2 = "За липень 2025",
                    valueSewerage2 = "584 грн.",
                    iconSewerage2 = Icons.Default.ArrowCircleDown, iconTintSewerage2 = Color(0xFF47954A), iconBgColorSewerage2 = Color(0xFFF1FFDE),
                    cardColorSewerage2 = Color(0xFFF1FFDE),
                    textColorSewerage2 = Color(0xFF4CAF50)
                )

                ColumnElementSewerage(
                    titleSewerage2 = "За увесь рік",
                    subtitleSewerage2 = "За 2025",
                    valueSewerage2 = "4789 грн.",
                    iconSewerage2 = Icons.Default.Wallet, iconTintSewerage2 = Color(0xFF67489E), iconBgColorSewerage2 = Color(0xFFF3E1FF),
                    cardColorSewerage2 = Color(0xFFF3E1FF),
                    textColorSewerage2 = Color(0xFF673AB7)
                )
            }
        }
    }
}

@Composable
fun RowElementSewerage(titleSewerage1: String,
               subtitleSewerage1: String,
               valueSewerage1: String,
               iconSewerage1: ImageVector, iconTintSewerage1: Color, iconBgColorSewerage1: Color,
               modifierSewerage1: Modifier = Modifier){
    Card(
        modifier = Modifier.size(width = 150.dp, height = 115.dp),
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
                        .background(iconBgColorSewerage1, CircleShape)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = iconSewerage1,
                            contentDescription = null,
                            tint = iconTintSewerage1,
                            modifier = modifierSewerage1.size(24.dp)
                        )
                    }
                }

                Text(text = valueSewerage1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titleSewerage1,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitleSewerage1,
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun ColumnElementSewerage(
    titleSewerage2: String,
    subtitleSewerage2: String,
    valueSewerage2: String,
    iconSewerage2: ImageVector, iconTintSewerage2: Color, iconBgColorSewerage2: Color,
    cardColorSewerage2: Color, textColorSewerage2: Color
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
                    .background(iconBgColorSewerage2, CircleShape)
            ) {
                Icon(
                    imageVector = iconSewerage2,
                    contentDescription = null,
                    tint = iconTintSewerage2,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titleSewerage2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1C1E)
                )
                Text(
                    text = subtitleSewerage2,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColorSewerage2
                )
            ) {
                Text(
                    text = valueSewerage2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorSewerage2,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}