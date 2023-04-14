package com.example.jetweather.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.util.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val settingsDataStore: SettingsDataStore) :
    ViewModel() {

    private var _unit = MutableStateFlow("")
    val unit = _unit.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _unit.value = settingsDataStore.readUnit()
            }
        }
    }

    fun writeUnit(unit:String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                 try {
                    settingsDataStore.writeUnit(unit)
                } catch (e: Exception) {
                    Log.e("Write Unit",e.message.toString())
                }
            }
        }
    }
}