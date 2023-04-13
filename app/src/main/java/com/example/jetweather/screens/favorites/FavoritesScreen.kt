package com.example.jetweather.screens.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.components.FavoriteItem
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites = favoritesViewModel.favList.collectAsState()

    Scaffold(topBar = {
        WeatherTopBar(
            title = "Favorites",
            isMainScreen = false,
            onButtonClicked = { navController.popBackStack() },
            navController = navController
        )
    }) {
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            modifier = Modifier.padding(it)
        ) {
            items(items = favorites.value) { favorite ->
                FavoriteItem(
                    favoriteModel = favorite,
                    navController = navController,
                    onDelete = { favoritesViewModel.deleteFavorite(favorite) },
                )
            }
        }
    }

}