package com.example.jetweather.network

import com.example.jetweather.models.Weather
import com.example.jetweather.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("/data/2.5/forecast")
    suspend fun getWeather(
        @Query(value = "q") query: String,
        @Query(value = "units") units: String,
        @Query(value = "appid") appid: String = API_KEY
    ): Weather

}