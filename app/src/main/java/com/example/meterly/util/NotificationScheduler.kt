package com.example.meterly.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.Calendar

object NotificationScheduler {
    private const val REQUEST_CODE_START = 1001
    private const val REQUEST_CODE_END = 1002
    private const val REQUEST_CODE_TEST = 9999
    private const val TAG = "NotificationScheduler"

    private fun createPendingIntent(context: Context, reminderType: String, requestCode: Int): PendingIntent {
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(ReminderReceiver.EXTRA_REMINDER_TYPE, reminderType)
        }
        return PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun scheduleTestNotification(context: Context) {
        Log.d(TAG, "scheduleTestNotification: scheduling in 5 seconds")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val triggerTime = System.currentTimeMillis() + 5000L

        cancelAlarm(context, REQUEST_CODE_TEST)

        val pendingIntent = createPendingIntent(context, ReminderReceiver.TYPE_TEST, REQUEST_CODE_TEST)
        Log.d(TAG, "scheduleTestNotification: triggerTime=$triggerTime, pendingIntent=$pendingIntent")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                Log.d(TAG, "scheduleTestNotification: setExactAndAllowWhileIdle called")
            } catch (e: SecurityException) {
                Log.e(TAG, "scheduleTestNotification: SecurityException, falling back to setAlarmClock", e)
                val alarmInfo = AlarmManager.AlarmClockInfo(triggerTime, pendingIntent)
                alarmManager.setAlarmClock(alarmInfo, pendingIntent)
                Log.d(TAG, "scheduleTestNotification: setAlarmClock called")
            }
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Log.d(TAG, "scheduleTestNotification: setExact called")
        }
    }

    fun scheduleStartReminder(context: Context, day: Int, hour: Int, minute: Int) {
        Log.d(TAG, "scheduleStartReminder: day=$day, hour=$hour, minute=$minute")
        scheduleExactAlarm(context, ReminderReceiver.TYPE_START, day, hour, minute, REQUEST_CODE_START)
    }

    fun scheduleEndReminder(context: Context, day: Int, hour: Int, minute: Int) {
        Log.d(TAG, "scheduleEndReminder: day=$day, hour=$hour, minute=$minute")
        scheduleExactAlarm(context, ReminderReceiver.TYPE_END, day, hour, minute, REQUEST_CODE_END)
    }

    private fun scheduleExactAlarm(
        context: Context,
        reminderType: String,
        day: Int,
        hour: Int,
        minute: Int,
        requestCode: Int
    ) {
        Log.d(TAG, "scheduleExactAlarm: reminderType=$reminderType, day=$day, hour=$hour, minute=$minute, requestCode=$requestCode")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        cancelAlarm(context, requestCode)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        Log.d(TAG, "scheduleExactAlarm: initial calendar.timeInMillis=${calendar.timeInMillis}")

        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val clampedDay = if (day > maxDay) maxDay else day
        calendar.set(Calendar.DAY_OF_MONTH, clampedDay)
        Log.d(TAG, "scheduleExactAlarm: after setting day=$clampedDay, calendar.timeInMillis=${calendar.timeInMillis}")

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.MONTH, 1)
            val newMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            calendar.set(Calendar.DAY_OF_MONTH, if (day > newMaxDay) newMaxDay else day)
            Log.d(TAG, "scheduleExactAlarm: moved to next month, new time=${calendar.timeInMillis}")
        }

        val pendingIntent = createPendingIntent(context, reminderType, requestCode)
        Log.d(TAG, "scheduleExactAlarm: pendingIntent=$pendingIntent, scheduled for=${calendar.timeInMillis}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
                Log.d(TAG, "scheduleExactAlarm: setExactAndAllowWhileIdle called")
            } catch (e: SecurityException) {
                Log.e(TAG, "scheduleExactAlarm: SecurityException, falling back to set()", e)
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
                Log.d(TAG, "scheduleExactAlarm: fallback set() called")
            }
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            Log.d(TAG, "scheduleExactAlarm: setExact called")
        }
    }

    fun cancelStartReminder(context: Context) {
        Log.d(TAG, "cancelStartReminder")
        cancelAlarm(context, REQUEST_CODE_START)
    }

    fun cancelEndReminder(context: Context) {
        Log.d(TAG, "cancelEndReminder")
        cancelAlarm(context, REQUEST_CODE_END)
    }

    private fun cancelAlarm(context: Context, requestCode: Int) {
        Log.d(TAG, "cancelAlarm: requestCode=$requestCode")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent)
            pendingIntent.cancel()
            Log.d(TAG, "cancelAlarm: cancelled")
        } else {
            Log.d(TAG, "cancelAlarm: no existing PendingIntent found")
        }
    }
}
