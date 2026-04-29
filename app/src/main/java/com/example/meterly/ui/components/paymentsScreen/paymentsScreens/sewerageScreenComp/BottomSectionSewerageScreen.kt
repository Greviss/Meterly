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
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.gasScreenComp.ColumnElem

@Composable
fun BottomSectionSewerageScreen(){
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
            colors = CardDefaults.cardColors(containerColor = Color.White)
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
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                ColumnElem(
                    titleSewerage = "Витрачено",
                    subtitleSewerage = "В цьому місяці",
                    iconSewerage = Icons.Default.Wallet,
                    iconBgColorSewerage = Color(0xFFFFBFBD),
                    iconTintSewerage = Color(0xFFF44336),
                    valueSewerage = "0 м³",
                    textColorSewerage = Color(0xFFF44336),
                    cardColorSewerage = Color(0xFFDC8E89),
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColumnElem(
                    titleSewerage = "Витрачено",
                    subtitleSewerage = "В попередньому місяці",
                    iconSewerage = Icons.Default.Wallet,
                    iconBgColorSewerage = Color(0xFFFFEFC1),
                    iconTintSewerage = Color(0xFFFF9800),
                    valueSewerage = "11 м³",
                    textColorSewerage = Color(0xFFFF9800),
                    cardColorSewerage = Color(0xFFFFD797),
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColumnElem(
                    titleSewerage = "До сплати",
                    subtitleSewerage = "В цьому місяці",
                    iconSewerage = Icons.Default.Money,
                    iconBgColorSewerage = Color(0xFFE2FFBE),
                    iconTintSewerage = Color(0xFF4CAF50),
                    valueSewerage = "0 грн.",
                    textColorSewerage = Color(0xFF4CAF50),
                    cardColorSewerage = Color(0xFFB6DB8B),
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColumnElem(
                    titleSewerage = "До сплати",
                    subtitleSewerage = "В попередньому місяці",
                    iconSewerage = Icons.Default.Money,
                    iconBgColorSewerage = Color(0xFF4CAF50),
                    iconTintSewerage = Color(0xFFE2FFBE),
                    valueSewerage = "374 грн.",
                    textColorSewerage = Color(0xFFB6DB8B),
                    cardColorSewerage = Color(0xFF4CAF50),
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColumnElem(
                    titleSewerage = "Тариф",
                    subtitleSewerage = "Актуальна ціна тарифа",
                    iconSewerage = Icons.Default.AutoGraph,
                    iconBgColorSewerage = Color(0xFFBBE0FF),
                    iconTintSewerage = Color(0xFF3F51B5),
                    valueSewerage = "34 грн./м³",
                    textColorSewerage = Color(0xFF3F51B5),
                    cardColorSewerage = Color(0xFF87B4D7),
                )
            }
        }
        ReceiptPickerItem3(hasReceipt = false, fileName = null)
    }
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
                    color = Color(0xFF1A1C1E)
                )
                Text(
                    text = subtitleSewerage,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
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

@Composable
fun ReceiptPickerItem3(hasReceipt: Boolean, fileName: String? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable{},
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (hasReceipt) Color(0xFF4CAF50) else Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (hasReceipt)
                        Icons.Default.Check
                    else
                        Icons.Default.AttachFile,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (hasReceipt)
                        "Квитанцію прикріплено"
                    else
                        "Прикріпити квитанцію",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                if (hasReceipt && fileName != null) {
                    Text(
                        text = fileName,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}