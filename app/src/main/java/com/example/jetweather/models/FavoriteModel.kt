package com.example.jetweather.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tb")
data class FavoriteModel(
    @PrimaryKey
    val city: String,
    val country: String
) {
}