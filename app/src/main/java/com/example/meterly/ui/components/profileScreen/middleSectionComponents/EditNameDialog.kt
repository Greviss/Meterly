package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun EditNameDialog(
    currentName: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var fullName by remember {
        mutableStateOf(currentName)
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Змінити ПІБ")
        },

        text = {
            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                },
                singleLine = true,
                label = {
                    Text("Нове ПІБ")
                },
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
            TextButton(
                onClick = {
                    onSave(fullName.trim())
                }
            ) {
                Text("Зберегти")
            }
        },

        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Скасувати")
            }
        }
    )
}