package com.example.meterly.ui.components.profileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSectionProfileScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {},
            border = BorderStroke(1.dp, Color(0xFFF44336)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(width = 150.dp, height = 45.dp)
        ) {
            Text(
                text = "Вийти",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF44336)
            )
        }
    }
}