package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.meterly.viewModel.SettingsViewModel

@Composable
fun Settings(
    settingsViewModel: SettingsViewModel,
    onClickProfileControl: () -> Unit,
    currentTheme: String,
    onThemeClick: () -> Unit
){
    val state by settingsViewModel.state.collectAsState()
    val context = LocalContext.current
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }
    var showStartDayPicker by remember { mutableStateOf(false) }
    var showEndDayPicker by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    LaunchedEffect(Unit) {
        settingsViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun requestNotificationPermissionIfNeeded(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                false
            } else true
        } else true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ReminderBegin(
            checked = state.reminderStartEnabled,
            onCheckedChange = { enabled ->
                if (!enabled || requestNotificationPermissionIfNeeded()) {
                    settingsViewModel.setReminderStartEnabled(enabled)
                }
            }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ReminderDayBegin(
            day = state.reminderStartDay,
            onClick = { showStartDayPicker = true }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ReminderTimeBegin(
            time = String.format("%02d:%02d", state.reminderStartHour, state.reminderStartMinute),
            onClick = { showStartTimePicker = true }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ReminderEnd(
            checked = state.reminderEndEnabled,
            onCheckedChange = { enabled ->
                if (!enabled || requestNotificationPermissionIfNeeded()) {
                    settingsViewModel.setReminderEndEnabled(enabled)
                }
            }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ReminderDayEnd(
            day = state.reminderEndDay,
            onClick = { showEndDayPicker = true }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ReminderTimeEnd(
            time = String.format("%02d:%02d", state.reminderEndHour, state.reminderEndMinute),
            onClick = { showEndTimePicker = true }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Rounding(
            checked = state.roundAmounts,
            onCheckedChange = { settingsViewModel.setRoundAmounts(it) }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Theme(
            currentTheme = currentTheme,
            onClick = onThemeClick
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ProfileControl(onClickProfileControl = onClickProfileControl)
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        TestNotificationButton(
            onClick = {
                if (requestNotificationPermissionIfNeeded()) {
                    settingsViewModel.sendTestNotification()
                }
            }
        )
    }

    if (showStartTimePicker) {
        TimePickerDialog(
            title = "Час нагадування на початку",
            initialHour = state.reminderStartHour,
            initialMinute = state.reminderStartMinute,
            onDismiss = { showStartTimePicker = false },
            onConfirm = { hour, minute ->
                settingsViewModel.setReminderStartTime(hour, minute)
                showStartTimePicker = false
            }
        )
    }

    if (showEndTimePicker) {
        TimePickerDialog(
            title = "Час нагадування в кінці",
            initialHour = state.reminderEndHour,
            initialMinute = state.reminderEndMinute,
            onDismiss = { showEndTimePicker = false },
            onConfirm = { hour, minute ->
                settingsViewModel.setReminderEndTime(hour, minute)
                showEndTimePicker = false
            }
        )
    }

    if (showStartDayPicker) {
        DayPickerDialog(
            title = "Початок місяця",
            days = listOf(1, 2, 3, 4, 5),
            initialDay = state.reminderStartDay,
            onDismiss = { showStartDayPicker = false },
            onConfirm = { day ->
                settingsViewModel.setReminderStartDay(day)
                showStartDayPicker = false
            }
        )
    }

    if (showEndDayPicker) {
        DayPickerDialog(
            title = "Кінець місяця",
            days = listOf(28, 29, 30, 31),
            initialDay = state.reminderEndDay,
            onDismiss = { showEndDayPicker = false },
            onConfirm = { day ->
                settingsViewModel.setReminderEndDay(day)
                showEndDayPicker = false
            }
        )
    }
}

@Composable
fun ReminderBegin(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Нагадування на початку міс.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = if (checked) "Увімкнено" else "Вимкнено",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun ReminderDayBegin(
    day: Int,
    onClick: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "День нагадування на початку",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "$day число",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun ReminderEnd(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Нагадування в кінці міс.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = if (checked) "Увімкнено" else "Вимкнено",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun ReminderDayEnd(
    day: Int,
    onClick: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "День нагадування в кінці",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "$day число",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun Rounding(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Округлення суми",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = if (checked) "Увімкнено" else "Вимкнено",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun ReminderTimeBegin(
    time: String,
    onClick: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Час нагадування на початку",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = time,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp))
    }
}

@Composable
fun ReminderTimeEnd(
    time: String,
    onClick: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Час нагадування в кінці",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = time,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp))
    }
}

@Composable
fun Theme(
    currentTheme: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column {
            Text(
                text = "Тема додатка",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = currentTheme,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun TestNotificationButton(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "Тестове сповіщення",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Прийде через 5 секунд",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun ProfileControl(onClickProfileControl: () -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ onClickProfileControl() }
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Керування профілем",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "Керувати",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp))
    }
}
