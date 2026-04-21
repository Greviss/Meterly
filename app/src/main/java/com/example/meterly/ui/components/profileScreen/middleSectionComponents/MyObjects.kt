package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyObjects(){
    AddObjects()
}

@Composable
fun AddObjects() {
    Column {
        Text("Список адрес, які ви прикріпили")
        FloatingActionButton(
            onClick = {},
            containerColor = Color(0xFF6200EE),
            contentColor = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }
}