package com.example.meterly.ui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meterly.ui.components.registrationScreen.BottomSection
import com.example.meterly.ui.components.registrationScreen.MiddleSection
import com.example.meterly.ui.components.registrationScreen.TopSection
import com.example.meterly.ui.theme.PrimaryGradient

@Composable
@Preview
fun RegisterScreen(
    onRegisterClick: (String, String, String) -> Unit = { _, _, _ -> },
    onLoginClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onTermsClick: () -> Unit = {}
) {
    var nameReg by remember {mutableStateOf("")}
    var addressReg by remember {mutableStateOf("")}
    var phoneNumberReg by remember {mutableStateOf("")}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryGradient())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSection()

            Spacer(modifier = Modifier.height(16.dp))

            MiddleSection (
                nameReg = nameReg,
                addressReg = addressReg,
                phoneNumberReg = phoneNumberReg,
                onNameRegChange = { nameReg = it },
                onAddressRegChange = { addressReg = it },
                onPhoneRegChange = {
                    if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                        phoneNumberReg = it
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSection(
                onLoginClick = onLoginClick
            )
        }
    }
}