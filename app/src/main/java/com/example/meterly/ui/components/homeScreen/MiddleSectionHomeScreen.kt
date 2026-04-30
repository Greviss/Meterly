package com.example.meterly.ui.components.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.R

@Composable
fun MiddleSectionHomeScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(R.drawable.home4),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 125.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.wrapContentSize()
            ) {
                ButtonUtil(
                    image = painterResource(R.drawable.icon_gas),
                    text = "Газ",
                    contentColor = Color(0xFF89C3F1)
                )

                Spacer(modifier = Modifier.width(20.dp))

                ButtonUtil(
                    image = painterResource(R.drawable.icon_water),
                    text = "Вода",
                    contentColor = Color(0xFF74C9D4)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.wrapContentSize()
            ) {
                ButtonUtil(
                    image = painterResource(R.drawable.icon_light),
                    text = "Світло",
                    contentColor = Color(0xFFEEE384)
                )

                Spacer(modifier = Modifier.width(20.dp))

                ButtonUtil(
                    image = painterResource(R.drawable.icon_sewerage),
                    text = "Канал.",
                    contentColor = Color(0xFF589788)
                )
            }
        }
    }
}
@Composable
fun ButtonUtil(image: Painter,
               text: String,
               contentColor: Color){
    Button (
        onClick = {},
        modifier = Modifier.size(110.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = contentColor)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
        }
    }
}