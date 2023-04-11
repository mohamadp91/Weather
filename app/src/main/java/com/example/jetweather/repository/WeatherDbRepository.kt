package com.example.jetweather.repository

import com.example.jetweather.data.db.FavoritesDao
import com.example.jetweather.models.FavoriteModel

class WeatherDbRepository(private val favoritesDao: FavoritesDao) {

    fun getAllFavorites() = favoritesDao.getAllFavorites()
    suspend fun getFavoriteById(city: String) = favoritesDao.getFavoriteByCity(city)
    suspend fun deleteFavorite(favoriteModel: FavoriteModel) = favoritesDao.deleteFavorite(favoriteModel)
    suspend fun updateFavorite(favoriteModel: FavoriteModel) = favoritesDao.updateFavorite(favoriteModel)
    suspend fun insertFavorite(favoriteModel: FavoriteModel) = favoritesDao.insertFavorite(favoriteModel)
}