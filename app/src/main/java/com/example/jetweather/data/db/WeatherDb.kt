package com.example.jetweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetweather.models.FavoriteModel

@Database(
    entities = [FavoriteModel::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {
    abstract fun favoriteDao(): WeatherDao

    companion object {
        @Volatile
        private var instance: WeatherDb? = null

        fun getInstance(context: Context): WeatherDb {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDb::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
        }

    }
}