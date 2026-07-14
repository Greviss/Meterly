package com.example.meterly.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.meterly.model.ThemeMode
import com.example.meterly.repository.ThemePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>()

    val theme = ThemePreferences
        .getTheme(context)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ThemeMode.SYSTEM
        )

    fun setTheme(mode: ThemeMode){
        viewModelScope.launch {
            ThemePreferences.saveTheme(
                context,
                mode
            )
        }
    }
}