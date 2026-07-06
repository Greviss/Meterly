package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun AddAddressDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var address by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Нова адреса") },
        text = {
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Введіть адресу") }
            )
        },

        confirmButton = {
            Button(
                onClick = {
                    if (address.isNotBlank()) {
                        onSave(address.trim())
                    }
                }
            ) {
                Text("Зберегти")
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Скасувати") }
        }
    )
}