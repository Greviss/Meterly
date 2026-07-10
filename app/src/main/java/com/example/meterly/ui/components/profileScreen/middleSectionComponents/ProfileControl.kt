package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.ui.theme.secondaryGradient

@Composable
fun ProfileControl(
    onEditName: () -> Unit,
    onSignOut: () -> Unit,
    onShare: () -> Unit,
    onDeleteAccount: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { onBackClick() }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Керування профілем",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                ProfileItem(
                    icon = Icons.Default.Person,
                    title = "Змінити ПІБ",
                    onClick = onEditName
                )

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.Logout,
                    title = "Увійти в інший акаунт",
                    onClick = onSignOut
                )

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.Share,
                    title = "Поділитися додатком",
                    onClick = onShare
                )

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.Delete,
                    title = "Видалити акаунт",
                    textColor = Color.Red,
                    iconColor = Color.Red,
                    onClick = onDeleteAccount
                )
            }
        }
    }
}

@Composable
private fun ProfileItem(
    icon: ImageVector,
    title: String,
    textColor: Color = Color.Black,
    iconColor: Color = Color.Gray,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(18.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.Medium
        )

        androidx.compose.material3.Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = iconColor
        )
    }
}