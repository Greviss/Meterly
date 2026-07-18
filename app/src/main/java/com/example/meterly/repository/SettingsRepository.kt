package com.example.meterly.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")
object SettingsRepository {
    private val REMINDER_START_ENABLED = booleanPreferencesKey("reminder_start_enabled")
    private val REMINDER_END_ENABLED = booleanPreferencesKey("reminder_end_enabled")
    private val ROUND_AMOUNTS = booleanPreferencesKey("round_amounts")
    private val REMINDER_START_DAY = intPreferencesKey("reminder_start_day")
    private val REMINDER_END_DAY = intPreferencesKey("reminder_end_day")
    private val REMINDER_START_HOUR = intPreferencesKey("reminder_start_hour")
    private val REMINDER_START_MINUTE = intPreferencesKey("reminder_start_minute")
    private val REMINDER_END_HOUR = intPreferencesKey("reminder_end_hour")
    private val REMINDER_END_MINUTE = intPreferencesKey("reminder_end_minute")

    fun reminderStartEnabled(context: Context): Flow<Boolean> = context.dataStore.data.map {
        it[REMINDER_START_ENABLED] ?: false
    }

    fun reminderEndEnabled(context: Context): Flow<Boolean> = context.dataStore.data.map {
        it[REMINDER_END_ENABLED] ?: false
    }

    fun roundAmounts(context: Context): Flow<Boolean> = context.dataStore.data.map {
        it[ROUND_AMOUNTS] ?: false
    }

    fun reminderStartDay(context: Context): Flow<Int> = context.dataStore.data.map {
        it[REMINDER_START_DAY] ?: 1
    }

    fun reminderEndDay(context: Context): Flow<Int> = context.dataStore.data.map {
        it[REMINDER_END_DAY] ?: 28
    }

    fun reminderStartHour(context: Context): Flow<Int> = context.dataStore.data.map {
        it[REMINDER_START_HOUR] ?: 20
    }

    fun reminderStartMinute(context: Context): Flow<Int> = context.dataStore.data.map {
        it[REMINDER_START_MINUTE] ?: 0
    }

    fun reminderEndHour(context: Context): Flow<Int> = context.dataStore.data.map {
        it[REMINDER_END_HOUR] ?: 18
    }

    fun reminderEndMinute(context: Context): Flow<Int> = context.dataStore.data.map {
        it[REMINDER_END_MINUTE] ?: 0
    }

    suspend fun setReminderStartEnabled(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[REMINDER_START_ENABLED] = enabled }
    }

    suspend fun setReminderEndEnabled(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[REMINDER_END_ENABLED] = enabled }
    }

    suspend fun setRoundAmounts(context: Context, round: Boolean) {
        context.dataStore.edit { it[ROUND_AMOUNTS] = round }
    }

    suspend fun setReminderStartDay(context: Context, day: Int) {
        context.dataStore.edit { it[REMINDER_START_DAY] = day }
    }

    suspend fun setReminderEndDay(context: Context, day: Int) {
        context.dataStore.edit { it[REMINDER_END_DAY] = day }
    }

    suspend fun setReminderStartTime(context: Context, hour: Int, minute: Int) {
        context.dataStore.edit {
            it[REMINDER_START_HOUR] = hour
            it[REMINDER_START_MINUTE] = minute
        }
    }

    suspend fun setReminderEndTime(context: Context, hour: Int, minute: Int) {
        context.dataStore.edit {
            it[REMINDER_END_HOUR] = hour
            it[REMINDER_END_MINUTE] = minute
        }
    }

    data class ReminderSettings(
        val startEnabled: Boolean,
        val endEnabled: Boolean,
        val startDay: Int,
        val endDay: Int,
        val startHour: Int,
        val startMinute: Int,
        val endHour: Int,
        val endMinute: Int
    )

    suspend fun loadReminderSettings(context: Context): ReminderSettings {
        val prefs = context.dataStore.data.first()
        return ReminderSettings(
            startEnabled = prefs[REMINDER_START_ENABLED] ?: false,
            endEnabled = prefs[REMINDER_END_ENABLED] ?: false,
            startDay = prefs[REMINDER_START_DAY] ?: 1,
            endDay = prefs[REMINDER_END_DAY] ?: 28,
            startHour = prefs[REMINDER_START_HOUR] ?: 20,
            startMinute = prefs[REMINDER_START_MINUTE] ?: 0,
            endHour = prefs[REMINDER_END_HOUR] ?: 18,
            endMinute = prefs[REMINDER_END_MINUTE] ?: 0
        )
    }
}
