package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ReminderBegin()
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        ReminderEnd()
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        Rounding()
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        ReminderTimeBegin()
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        ReminderTimeEnd()
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        Theme()
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        ProfileControl()
    }
}

@Composable
fun ReminderBegin(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Нагадування на початку міс.", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Увімкнено", fontSize = 13.sp, color = Color.Gray)
        }
        Switch(checked = true, onCheckedChange = {})
    }
}

@Composable
fun ReminderEnd() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Нагадування в кінці міс.", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Увімкнено", fontSize = 13.sp, color = Color.Gray)
        }
        Switch(checked = true, onCheckedChange = {})
    }
}

@Composable
fun Rounding(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Округлення суми", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Вимкнено", fontSize = 13.sp, color = Color.Gray)
        }
        Switch(checked = false, onCheckedChange = {})
    }
}

@Composable
fun ReminderTimeBegin(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Час нагадування на початку", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "20:00", fontSize = 13.sp, color = Color.Gray)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
    }
}

@Composable
fun ReminderTimeEnd(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Час нагадування в кінці", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "18:00", fontSize = 13.sp, color = Color.Gray)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
    }
}

@Composable
fun Theme(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Тема додатка", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Як у системі", fontSize = 13.sp, color = Color.Gray)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
    }
}

@Composable
fun ProfileControl(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Керування профілем", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Керувати", fontSize = 13.sp, color = Color.Gray)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
    }
}