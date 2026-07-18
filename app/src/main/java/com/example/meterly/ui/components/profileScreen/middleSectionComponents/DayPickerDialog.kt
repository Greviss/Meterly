package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DayPickerDialog(
    title: String,
    days: List<Int>,
    initialDay: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    val selectedDay = remember { mutableIntStateOf(if (days.contains(initialDay)) initialDay else days.first()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                days.forEach { day ->
                    DayItem(
                        day = day,
                        selected = selectedDay.intValue == day,
                        onClick = { selectedDay.intValue = day }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(selectedDay.intValue)
            }) {
                Text("Зберегти")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
        }
    )
}

@Composable
private fun DayItem(
    day: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
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
            text = "$day",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
