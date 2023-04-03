package com.example.jetweather.models

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val weatherForecastList: List<WeatherForecast>,
    val message: Int
)