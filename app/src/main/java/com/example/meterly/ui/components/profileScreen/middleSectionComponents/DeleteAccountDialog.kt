package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DeleteAccountDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Видалити акаунт")
        },
        text = {
            Text("Після видалення буде втрачено всі адреси та дані профілю.")
        },

        confirmButton = {
            TextButton(
                onClick = onDelete
            ) {
                Text(
                    "Видалити",
                    color = Color.Red
                )
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