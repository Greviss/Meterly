package com.example.meterly.ui.components.paymentsScreen.paymentsScreens.sewerageScreenComp

import android.R.attr.onClick
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSectionSewerageScreen(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
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

                Costs3()

                Spacer(modifier = Modifier.height(8.dp))

                Payable3()

                Spacer(modifier = Modifier.height(8.dp))

                СurrentTariff3()
            }
        }
        ReceiptPickerItem3(hasReceipt = false, fileName = null)
    }
}

@Composable
fun Costs3(){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.wrapContentSize()
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Витрачено:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp))

            Spacer(modifier = Modifier.width(184.dp))

            Text(
                text = "0 м³",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4FA6EC),
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Минулого місяця:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp, bottom = 2.dp)
            )

            Spacer(modifier = Modifier.width(135.dp))

            Text(
                text = "13 м³",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4FA6EC),
                modifier = Modifier.padding(top = 3.dp, bottom = 2.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .height(3.dp)
                .padding(top = 6.dp),
            color = Color.DarkGray.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun Payable3(){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.wrapContentSize()
    ) {

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "До сплати:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp))

            Spacer(modifier = Modifier.width(180.dp))

            Text(
                text = "0 грн.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4FA6EC),
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Минулого місяця:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp, bottom = 4.dp)
            )

            Spacer(modifier = Modifier.width(120.dp))

            Text(
                text = "655 грн.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4FA6EC),
                modifier = Modifier.padding(top = 3.dp, bottom = 4.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .height(3.dp)
                .padding(top = 6.dp),
            color = Color.DarkGray.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun СurrentTariff3(){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.wrapContentSize()
    ) {

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Поточний тариф:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp))

            Spacer(modifier = Modifier.width(98.dp))

            Text(
                text = "31.8 грн./м³",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(top = 6.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .height(3.dp)
                .padding(top = 6.dp),
            color = Color.DarkGray.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun ReceiptPickerItem3(hasReceipt: Boolean, fileName: String? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick },
        shape = RoundedCornerShape(20.dp),
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
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}