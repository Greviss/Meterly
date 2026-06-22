package com.example.meterly.util

import android.content.Context
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        private val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
    }

    val onboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[ONBOARDING_COMPLETED] ?: false }

    suspend fun setOnboardingCompleted(completed: Boolean){
        context.dataStore.edit { prefs -> prefs[ONBOARDING_COMPLETED] = completed }
    }

    val biometricEnabled: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[BIOMETRIC_ENABLED] ?: false }

    suspend fun setBiometricEnabled(enabled: Boolean){
        context.dataStore.edit { prefs -> prefs[BIOMETRIC_ENABLED] = enabled }
    }

    suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}