package com.example.jetweather.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jetweather.data.ResultState
import com.example.jetweather.models.Weather
import com.example.jetweather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(val weatherApi: WeatherApi) {

    private var result: MutableState<ResultState<Weather>> = mutableStateOf(ResultState.Loading)

    suspend fun getWeather(
        cityName: String,
        units: String
    ): ResultState<Weather> {
        result.value = ResultState.Loading
        try {
            result.value = ResultState.Success(weatherApi.getWeather(cityName, units))
        } catch (e: Exception) {
            result.value = ResultState.Error(e)
        }
        return result.value
    }
}