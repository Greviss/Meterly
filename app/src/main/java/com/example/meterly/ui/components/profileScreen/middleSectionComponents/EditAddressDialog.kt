package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import com.example.meterly.model.Address

@Composable
fun EditAddressDialog(
    address: Address,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {

    var text by remember {
        mutableStateOf(address.address)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Редагувати адресу")
        },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },

        confirmButton = {
            Button(
                onClick = {
                    onSave(text)
                }
            ) {
                Text("Зберегти")
            }

        },

        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Скасувати")
            }

        }
    )
}