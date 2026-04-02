package com.example.meterly.ui.components.profileScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage

@Composable
fun TopSectionProfileScreen(imageUrl: String? = null){
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape),
        contentScale = ContentScale.Crop
    )
    val nickname = Text(
        text = "UserName",
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .padding(16.dp)
    )
}