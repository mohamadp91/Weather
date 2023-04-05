package com.example.jetweather.models

data class WeatherForecast(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherCondition>,
    val wind: Wind
)