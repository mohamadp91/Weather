package com.example.jetweather.models

data class WeatherObject(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Int
)