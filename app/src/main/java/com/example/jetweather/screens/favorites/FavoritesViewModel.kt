package com.example.jetweather.screens.favorites

import androidx.lifecycle.ViewModel
import com.example.jetweather.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) :
    ViewModel() {
}