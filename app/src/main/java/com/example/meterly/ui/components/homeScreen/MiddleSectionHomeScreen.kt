package com.example.meterly.ui.components.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
            painter = painterResource(R.drawable.home5),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 100.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.wrapContentSize()
            ) {
                ButtonUtil(
                    image = painterResource(R.drawable.icon_gas),
                    text = "Газ",
                    contentColor = Color(0xFFC2DEF5)
                )

                Spacer(modifier = Modifier.width(12.dp))

                ButtonUtil(
                    image = painterResource(R.drawable.icon_water),
                    text = "Вода",
                    contentColor = Color(0xFFC1E9EE)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.wrapContentSize()
            ) {
                ButtonUtil(
                    image = painterResource(R.drawable.icon_light),
                    text = "Світло",
                    contentColor = Color(0xFFECE7C1)
                )

                Spacer(modifier = Modifier.width(12.dp))

                ButtonUtil(
                    image = painterResource(R.drawable.icon_sewerage),
                    text = "Канал.",
                    contentColor = Color(0xFFC1EED2)
                )
            }
        }
    }
}
@Composable
fun ButtonUtil(
    image: Painter,
    text: String,
    contentColor: Color
) {
    Button(
        onClick = {},
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = contentColor),
        contentPadding = PaddingValues(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Переглянути платежі",
                    fontSize = 10.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowCircleRight,
                contentDescription = null,
                tint = Color(0xFF808080),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}