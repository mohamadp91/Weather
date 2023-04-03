package com.example.jetweather.screens.main

import androidx.lifecycle.ViewModel
import com.example.jetweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {
    suspend fun getWeather(cityName: String, units: String) =
        weatherRepository.getWeather(cityName, units)
}