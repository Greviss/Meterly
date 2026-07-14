package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meterly.model.ThemeMode

@Composable
fun ThemeDialog(
    currentTheme: ThemeMode,
    onDismiss: () -> Unit,
    onSave: (ThemeMode) -> Unit
) {
    val selected = remember {
        mutableStateOf(currentTheme)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Тема додатка")
        },
        text = {
            Column {
                ThemeItem(
                    text = "Як у системі",
                    selected = selected.value == ThemeMode.SYSTEM
                ) {
                    selected.value = ThemeMode.SYSTEM
                }

                ThemeItem(
                    text = "Світла",
                    selected = selected.value == ThemeMode.LIGHT
                ) {
                    selected.value = ThemeMode.LIGHT
                }

                ThemeItem(
                    text = "Темна",
                    selected = selected.value == ThemeMode.DARK
                ) {
                    selected.value = ThemeMode.DARK
                }
            }
        },

        confirmButton = {
            TextButton(
                onClick = {
                    onSave(selected.value)
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

@Composable
private fun ThemeItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick
            )
            .padding(vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}