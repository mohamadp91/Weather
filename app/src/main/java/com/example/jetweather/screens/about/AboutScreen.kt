package com.example.jetweather.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherTopBar(
            title = "About",
            isMainScreen = false,
            onButtonClicked = { navController.popBackStack() },
            navController = navController
        )
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(text = "Weather App v0.1")
        }
    }
}