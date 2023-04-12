package com.example.jetweather.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.models.FavoriteModel
import com.example.jetweather.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) :
    ViewModel() {
    private var _favList: MutableStateFlow<List<FavoriteModel>> = MutableStateFlow(emptyList())
    val favList get() = _favList.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherDbRepository.getAllFavorites().distinctUntilChanged().collectLatest {
                    if (it.isNotEmpty()) {
                        _favList.value = it
                    }
                }
            }
        }
    }

    fun deleteFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherDbRepository.deleteFavorite(favoriteModel)
            }
        }
    }

    fun insertFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherDbRepository.insertFavorite(favoriteModel)
            }
        }
    }

    fun updateFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherDbRepository.updateFavorite(favoriteModel)
            }
        }
    }

    fun getFavoriteByCityName(cityName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherDbRepository.getFavoriteByCity(cityName)
            }
        }
    }

    fun isFavorite(city: String): Boolean {
        return favList.value.any { it.city.equals(city, ignoreCase = true) }
    }
}