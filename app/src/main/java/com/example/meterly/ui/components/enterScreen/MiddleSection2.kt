package com.example.meterly.ui.components.enterScreen

import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun MiddleSection2(
    address2: String = "",
    phoneNumber: String = "",
    onAddressChange: (String) -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {},
    OnEnterClick: (String, String) -> Unit = { _, _ ->}
){
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.fillMaxWidth().height(325.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    text = "Вхід",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = address2,
                    onValueChange = onAddressChange,
                    label = {Text("Адреса проживання")},
                    placeholder = {Text("вул. Хрещатик, 1, кв. 10")},
                    leadingIcon = {
                        Icon(Icons.Default.Phone, null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1E88E5),
                        focusedLabelColor = Color(0xFF1E88E5)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    label = {Text("Номер телефону")},
                    placeholder = {Text("+380971111111")},
                    leadingIcon = {
                        Icon(Icons.Default.LocationOn, null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1E88E5),
                        focusedLabelColor = Color(0xFF1E88E5)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(36.dp))

                Button(onClick = {
                    if (address2.isNotBlank() && phoneNumber.length == 10){
                        OnEnterClick(address2, phoneNumber)
                    }
                },
                    enabled = address2.isNotBlank() && phoneNumber.length == 10,
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5),
                        contentColor = Color.Gray,
                        disabledContainerColor = Color(0xFFBDBDBD),
                        disabledContentColor = Color(0xFF9E9E9E)
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Text(
                        text = "Увійти",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}