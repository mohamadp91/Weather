package com.example.jetweather.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.data.ResultState
import com.example.jetweather.models.Weather

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val result =
            produceState<ResultState<Weather>>(initialValue = ResultState.Loading, producer = {
                value = mainViewModel.getWeather("moscow", "imperial")
            })
        println(result)
        // todo : we cant communicate with the url because of the unsecure url
        Text(text = result.value.toString())
    }
}