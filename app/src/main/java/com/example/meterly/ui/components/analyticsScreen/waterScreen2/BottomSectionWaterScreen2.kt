package com.example.meterly.ui.components.analyticsScreen.waterScreen2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSectionWaterScreen2(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(16.dp),
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                PercentWater()

                Spacer(modifier = Modifier.height(8.dp))

                EveryDayWater()

                Spacer(modifier = Modifier.height(8.dp))

                PickWater()

                Spacer(modifier = Modifier.height(8.dp))

                YearWater()
            }
        }
    }
}

@Composable
fun PercentWater(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            modifier = Modifier
                .height(3.dp),
            color = Color.DarkGray.copy(alpha = 0.5f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Економія",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp))

            Text(
                text = "0 грн.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF8BC34A),
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "У відсотках",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp, bottom = 4.dp)
            )

            Text(
                text = "0 %",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2196F3),
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
fun EveryDayWater(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Кожного дня в м³",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp))

            Text(
                text = "0,26 м³",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF8BC34A),
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Кожного дня в грн.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp, bottom = 4.dp)
            )

            Text(
                text = "3,62 грн.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2196F3),
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
fun PickWater(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Найбільше витрачено:",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp))

            Text(
                text = "496 грн.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF8BC34A),
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
fun YearWater() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Витрачено за рік:",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = "1248 грн.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF44336),
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