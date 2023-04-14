package com.example.jetweather.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun readUnit(): String = dataStore.data.map { preferences ->
        preferences[UNIT_KEY] ?: "metric"
    }.first()

    suspend fun writeUnit(username: String) {
        dataStore.edit { preferences ->
            preferences[UNIT_KEY] = username
        }
    }

    companion object {
        private val UNIT_KEY = stringPreferencesKey("unit")
    }
}