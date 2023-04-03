package com.example.jetweather.models

data class WeatherCondition(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)