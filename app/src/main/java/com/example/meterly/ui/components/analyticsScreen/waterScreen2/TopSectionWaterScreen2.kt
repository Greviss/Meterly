package com.example.meterly.ui.components.analyticsScreen.waterScreen2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun TopSectionWaterScreen2(
    onLeftArrowWater2: () -> Unit,
    onRightArrowWater2: () -> Unit
){
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon_water),
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onLeftArrowWater2,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleLeft,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            Text(
                text = "Вода",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Color.Black
            )

            IconButton(
                onClick = onRightArrowWater2,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleRight,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}