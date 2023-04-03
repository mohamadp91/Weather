package com.example.jetweather.di

import com.example.jetweather.network.WeatherApi
import com.example.jetweather.repository.WeatherRepository
import com.example.jetweather.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi) =
        WeatherRepository(weatherApi)
}