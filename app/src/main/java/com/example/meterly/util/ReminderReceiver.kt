package com.example.meterly.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.meterly.MainActivity
import com.example.meterly.R

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive: start")

        val reminderType = intent.getStringExtra(EXTRA_REMINDER_TYPE)
        Log.d(TAG, "onReceive: reminderType=$reminderType")
        if (reminderType == null) {
            Log.e(TAG, "onReceive: reminderType is null, returning")
            return
        }

        createNotificationChannel(context)
        Log.d(TAG, "onReceive: notification channel created")

        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        Log.d(TAG, "onReceive: pendingIntent created")

        val text = when (reminderType) {
            TYPE_START -> "Час передати показники лічильників."
            TYPE_END -> "Не забудьте оплатити комунальні послуги."
            TYPE_TEST -> "Тестове сповіщення! Нагадування працюють."
            else -> {
                Log.e(TAG, "onReceive: unknown reminderType=$reminderType")
                return
            }
        }

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        Log.d(TAG, "onReceive: soundUri=$soundUri")

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Нагадування")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
            .build()
        Log.d(TAG, "onReceive: notification built")

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(reminderType.hashCode(), notification)
        Log.d(TAG, "onReceive: notificationManager.notify() called, id=${reminderType.hashCode()}")
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Нагадування",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Щомісячні нагадування про показники"
                enableVibration(true)
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d(TAG, "createNotificationChannel: channel created")
        }
    }

    companion object {
        const val TAG = "ReminderReceiver"
        const val CHANNEL_ID = "meterly_reminders"
        const val EXTRA_REMINDER_TYPE = "reminder_type"
        const val TYPE_START = "start"
        const val TYPE_END = "end"
        const val TYPE_TEST = "test"
    }
}
