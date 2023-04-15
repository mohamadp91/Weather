package com.example.jetweather.repository

import com.example.jetweather.data.db.WeatherDao
import com.example.jetweather.models.FavoriteModel

class WeatherDbRepository(private val weatherDao: WeatherDao) {

    fun getAllFavorites() = weatherDao.getAllFavorites()
    suspend fun getFavoriteByCity(city: String) = weatherDao.getFavoriteByCity(city)
    suspend fun deleteFavorite(favoriteModel: FavoriteModel) =
        weatherDao.deleteFavorite(favoriteModel)

    suspend fun updateFavorite(favoriteModel: FavoriteModel) =
        weatherDao.updateFavorite(favoriteModel)

    suspend fun insertFavorite(favoriteModel: FavoriteModel) =
        weatherDao.insertFavorite(favoriteModel)
}