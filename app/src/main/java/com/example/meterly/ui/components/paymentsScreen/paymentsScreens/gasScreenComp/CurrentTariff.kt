package com.example.meterly.ui.components.paymentsScreen.paymentsScreens.gasScreenComp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrentTariff(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Поточний тариф:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 12.dp))

                Text(
                    text = "4.32 грн./м³",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
        }
    }
}