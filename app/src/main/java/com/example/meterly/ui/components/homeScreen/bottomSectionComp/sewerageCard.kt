package com.example.meterly.ui.components.homeScreen.bottomSectionComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.R

@Composable
@Preview
fun SewerageCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_sewerage),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Каналізація",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "До сплати: ",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "0 грн.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF4CAF50)
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopEnd),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color(0xFFEC8078))
            ) {
                Text(
                    text = "Не оплачено",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(6.dp)
                )
            }
        }
    }
}