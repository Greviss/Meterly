package com.example.meterly.ui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.meterly.ui.components.enterScreen.BottomSection2
import com.example.meterly.ui.components.enterScreen.MiddleSection2
import com.example.meterly.ui.components.enterScreen.TopSection2
import com.example.meterly.ui.theme.primaryGradient

@Composable
@Preview
fun EnterScreen(
    OnEnterClick: (String, String) -> Unit = { _, _ ->},
    addressEnt: String = "",
    phoneNumberEnt: String = "",
    onAddressEntChange: (String) -> Unit = {},
    onPhoneEntChange: (String) -> Unit = {},
    onSignIn: () -> Unit = {}
){
    var addressEnt by remember { mutableStateOf("") }
    var phoneNumberEnt by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryGradient())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            TopSection2()

            Spacer(modifier = Modifier.height(16.dp))

            MiddleSection2(
                addressEnt = addressEnt,
                phoneNumberEnt = phoneNumberEnt,
                onAddressEntChange = { addressEnt = it },
                onPhoneEntChange = {
                    if (it.length <= 10 && it.all { char -> char.isDigit() }){
                        phoneNumberEnt = it
                    }
                },
                OnEnterClick = OnEnterClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSection2(
                onSignIn = onSignIn
            )
        }
    }
}