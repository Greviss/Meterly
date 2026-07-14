package com.example.meterly.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.meterly.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object ThemePreferences {
    val Context.dataStore by preferencesDataStore("theme")
    private val THEME =
        stringPreferencesKey("theme_mode")

    suspend fun saveTheme(
        context: Context,
        theme: ThemeMode
    ) {
        context.dataStore.edit {
            it[THEME] = theme.name
        }
    }

    fun getTheme(context: Context): Flow<ThemeMode> {
        return context.dataStore.data.map {
            ThemeMode.valueOf(
                it[THEME] ?: ThemeMode.SYSTEM.name
            )
        }
    }
}