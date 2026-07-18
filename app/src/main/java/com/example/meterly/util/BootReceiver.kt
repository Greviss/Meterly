package com.example.meterly.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.meterly.repository.SettingsRepository
import kotlinx.coroutines.runBlocking

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return

        runBlocking {
            val settings = SettingsRepository.loadReminderSettings(context)
            if (settings.startEnabled) {
                NotificationScheduler.scheduleStartReminder(
                    context, settings.startDay, settings.startHour, settings.startMinute
                )
            }
            if (settings.endEnabled) {
                NotificationScheduler.scheduleEndReminder(
                    context, settings.endDay, settings.endHour, settings.endMinute
                )
            }
        }
    }
}
