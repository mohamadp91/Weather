package com.example.jetweather.di

import android.content.Context
import androidx.room.Room
import com.example.jetweather.data.db.FavoritesDao
import com.example.jetweather.data.db.WeatherDb
import com.example.jetweather.network.WeatherApi
import com.example.jetweather.repository.WeatherDbRepository
import com.example.jetweather.repository.WeatherRepository
import com.example.jetweather.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideWeatherDb(@ApplicationContext applicationContext: Context): WeatherDb =
        Room.databaseBuilder(
            applicationContext,
            WeatherDb::class.java,
            "weather_db"
        ).build()

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDb: WeatherDb) = weatherDb.favoriteDao()

    @Singleton
    @Provides
    fun provideWeatherDbRepository(favoritesDao: FavoritesDao) = WeatherDbRepository(favoritesDao)

}