package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.model.Address
import com.example.meterly.model.User

@Composable
fun PersonalDataComp(
    user: User?,
    currentAddress: Address?
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        PIB(user)

        HorizontalDivider(
            color = Color.LightGray.copy(alpha = 0.3f),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        PhoneNumber(user)

        HorizontalDivider(
            color = Color.LightGray.copy(alpha = 0.3f),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val addressString = currentAddress?.address?.ifBlank { "Адресу не вказано" } ?: "Адресу не вказано"

        AddressSection(addressText = addressString)
    }
}

@Composable
fun PIB(user: User?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Text(text = "ПІБ", fontSize = 13.sp, color = Color.Gray)
        Spacer(Modifier.height(4.dp))
        Text(
            text = user?.fullName ?: "",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PhoneNumber(user: User?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Text(text = "Номер телефону", fontSize = 13.sp, color = Color.Gray)
        Spacer(Modifier.height(4.dp))
        Text(
            text = user?.phoneNumber ?: "",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AddressSection(
    addressText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = "Адреса проживання",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = addressText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}