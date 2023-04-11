package com.example.jetweather.data.db

import androidx.room.*
import com.example.jetweather.models.FavoriteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteModel: FavoriteModel)

    @Query("SELECT * FROM fav_tb")
    fun getAllFavorites(): Flow<List<FavoriteModel>>

    @Delete
    suspend fun deleteFavorite(favoriteModel: FavoriteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteModel: FavoriteModel)

    @Query("Select * from  fav_tb where city = :city")
    suspend fun getFavoriteByCity(city: String): List<FavoriteModel>

}