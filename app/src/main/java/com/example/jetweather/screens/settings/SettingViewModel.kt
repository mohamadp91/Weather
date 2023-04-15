package com.example.jetweather.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.util.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val settingsDataStore: SettingsDataStore) :
    ViewModel() {

    suspend fun readUnit(): String = settingsDataStore.readUnit() ?: "metric"

    fun writeUnit(unit: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    settingsDataStore.writeUnit(unit)
                } catch (e: Exception) {
                    Log.e("Write Unit", e.message.toString())
                }
            }
        }
    }
}