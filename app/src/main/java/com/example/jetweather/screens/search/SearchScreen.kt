package com.example.jetweather.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jetweather.navigation.WeatherScreens
import com.example.jetweather.widgets.SearchTextField
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun SearchScreen(navController: NavHostController) {
    Scaffold(topBar = {
        WeatherTopBar(
            title = "Search",
            isMainScreen = false,
            onButtonClicked = {
                navController.popBackStack()
            },
            navController = navController
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            SearchTextField(
                onSearch = { cityName ->
                    navController.navigate(WeatherScreens.MainScreen.name + "/$cityName")
                },
            )
        }
    }
}

