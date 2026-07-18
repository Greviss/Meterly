package com.example.meterly.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.meterly.repository.SettingsRepository
import com.example.meterly.util.NotificationScheduler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SettingsState(
    val reminderStartEnabled: Boolean = false,
    val reminderEndEnabled: Boolean = false,
    val roundAmounts: Boolean = false,
    val reminderStartDay: Int = 1,
    val reminderEndDay: Int = 28,
    val reminderStartHour: Int = 20,
    val reminderStartMinute: Int = 0,
    val reminderEndHour: Int = 18,
    val reminderEndMinute: Int = 0
)

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>()
    private val repository = SettingsRepository

    private val _toastEvent = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val toastEvent: SharedFlow<String> = _toastEvent

    val state: StateFlow<SettingsState> = repository.reminderStartEnabled(context)
        .combine(repository.reminderEndEnabled(context)) { a, b ->
            SettingsState(reminderStartEnabled = a, reminderEndEnabled = b)
        }
        .combine(repository.roundAmounts(context)) { s, c ->
            s.copy(roundAmounts = c)
        }
        .combine(repository.reminderStartDay(context)) { s, d ->
            s.copy(reminderStartDay = d)
        }
        .combine(repository.reminderEndDay(context)) { s, e ->
            s.copy(reminderEndDay = e)
        }
        .combine(repository.reminderStartHour(context)) { s, f ->
            s.copy(reminderStartHour = f)
        }
        .combine(repository.reminderStartMinute(context)) { s, g ->
            s.copy(reminderStartMinute = g)
        }
        .combine(repository.reminderEndHour(context)) { s, h ->
            s.copy(reminderEndHour = h)
        }
        .combine(repository.reminderEndMinute(context)) { s, i ->
            s.copy(reminderEndMinute = i)
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SettingsState())

    fun setReminderStartEnabled(enabled: Boolean) {
        Log.d(TAG, "setReminderStartEnabled: enabled=$enabled")
        viewModelScope.launch {
            repository.setReminderStartEnabled(context, enabled)
            val s = state.value
            if (enabled) {
                Log.d(TAG, "setReminderStartEnabled: scheduling start, day=${s.reminderStartDay}, time=${s.reminderStartHour}:${s.reminderStartMinute}")
                NotificationScheduler.scheduleStartReminder(context, s.reminderStartDay, s.reminderStartHour, s.reminderStartMinute)
                showReminderToast("початку", s.reminderStartDay, s.reminderStartHour, s.reminderStartMinute)
            } else {
                Log.d(TAG, "setReminderStartEnabled: cancelling start")
                NotificationScheduler.cancelStartReminder(context)
            }
        }
    }

    fun setReminderEndEnabled(enabled: Boolean) {
        Log.d(TAG, "setReminderEndEnabled: enabled=$enabled")
        viewModelScope.launch {
            repository.setReminderEndEnabled(context, enabled)
            val s = state.value
            if (enabled) {
                Log.d(TAG, "setReminderEndEnabled: scheduling end, day=${s.reminderEndDay}, time=${s.reminderEndHour}:${s.reminderEndMinute}")
                NotificationScheduler.scheduleEndReminder(context, s.reminderEndDay, s.reminderEndHour, s.reminderEndMinute)
                showReminderToast("кінця", s.reminderEndDay, s.reminderEndHour, s.reminderEndMinute)
            } else {
                Log.d(TAG, "setReminderEndEnabled: cancelling end")
                NotificationScheduler.cancelEndReminder(context)
            }
        }
    }

    fun setRoundAmounts(round: Boolean) {
        viewModelScope.launch {
            repository.setRoundAmounts(context, round)
        }
    }

    fun setReminderStartDay(day: Int) {
        viewModelScope.launch {
            repository.setReminderStartDay(context, day)
            if (state.value.reminderStartEnabled) {
                val s = state.value
                NotificationScheduler.scheduleStartReminder(context, day, s.reminderStartHour, s.reminderStartMinute)
                showReminderToast("початку", day, s.reminderStartHour, s.reminderStartMinute)
            }
        }
    }

    fun setReminderEndDay(day: Int) {
        viewModelScope.launch {
            repository.setReminderEndDay(context, day)
            if (state.value.reminderEndEnabled) {
                val s = state.value
                NotificationScheduler.scheduleEndReminder(context, day, s.reminderEndHour, s.reminderEndMinute)
                showReminderToast("кінця", day, s.reminderEndHour, s.reminderEndMinute)
            }
        }
    }

    fun setReminderStartTime(hour: Int, minute: Int) {
        viewModelScope.launch {
            repository.setReminderStartTime(context, hour, minute)
            if (state.value.reminderStartEnabled) {
                val s = state.value
                NotificationScheduler.scheduleStartReminder(context, s.reminderStartDay, hour, minute)
                showReminderToast("початку", s.reminderStartDay, hour, minute)
            }
        }
    }

    fun setReminderEndTime(hour: Int, minute: Int) {
        viewModelScope.launch {
            repository.setReminderEndTime(context, hour, minute)
            if (state.value.reminderEndEnabled) {
                val s = state.value
                NotificationScheduler.scheduleEndReminder(context, s.reminderEndDay, hour, minute)
                showReminderToast("кінця", s.reminderEndDay, hour, minute)
            }
        }
    }

    fun sendTestNotification() {
        Log.d(TAG, "sendTestNotification: called")
        NotificationScheduler.scheduleTestNotification(context)
        _toastEvent.tryEmit("Тестове сповіщення заплановано через 5 секунд.")
    }

    private fun showReminderToast(whenText: String, day: Int, hour: Int, minute: Int) {
        val time = String.format("%02d:%02d", hour, minute)
        _toastEvent.tryEmit("Ваше нагадування прийде $day числа о $time.")
    }

    companion object {
        private const val TAG = "SettingsViewModel"
    }
}
