package com.example.meterly.ui.components.homeScreen.bottomSectionComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.R

@Composable
fun sewerageCard(){
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(size = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(R.drawable.icon_sewerage),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 85.dp, height = 85.dp)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 11.dp)) {
                Text(
                    text = "Каналізація",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Статус:",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }

            Text(
                modifier = Modifier.padding(start = 4.dp, bottom = 11.dp),
                text = "Не оплачено",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
        }
    }
}