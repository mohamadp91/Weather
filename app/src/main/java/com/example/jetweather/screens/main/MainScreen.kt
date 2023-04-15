package com.example.jetweather.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.components.HumidityWindPressureRow
import com.example.jetweather.components.SunriseSunsetRow
import com.example.jetweather.components.TopContent
import com.example.jetweather.components.WeatherHistory
import com.example.jetweather.data.ResultState
import com.example.jetweather.models.WeatherObject
import com.example.jetweather.screens.settings.SettingViewModel
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun MainScreen(
    navController: NavController,
    cityName: String,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val unit = produceState<String>(initialValue = "metric", producer = {
        value = settingViewModel.readUnit()
    })
    println("$unit    main screen")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val result =
            produceState<ResultState<WeatherObject>>(
                initialValue = ResultState.Loading,
                producer = {
                    value = mainViewModel.getWeather(cityName, unit.value)
                })
        when (result.value) {
            is ResultState.Loading -> CircularProgressIndicator(modifier = Modifier.size(50.dp))
            is ResultState.Success<*> -> MainScaffold(
                weatherObject = (result.value as ResultState.Success<WeatherObject>).data,
                navController = navController
            )
            is ResultState.Error -> Text(text = (result.value as ResultState.Error).exception.message.toString())
        }
    }
}

@Composable
fun MainScaffold(
    weatherObject: WeatherObject,
    navController: NavController
) {
    val weatherForecast = weatherObject.list[0]
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        WeatherTopBar(
            navController = navController,
            title = "${weatherObject.city.name}/${weatherObject.city.country}",
            isMainScreen = true,
            onButtonClicked = {},
        )
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(it)
        ) {
            TopContent(weatherForecast = weatherForecast)
            HumidityWindPressureRow(weatherForecast = weatherForecast)
            Divider(modifier = Modifier.fillMaxWidth())
            SunriseSunsetRow(
                sunrise = weatherObject.city.sunrise,
                sunset = weatherObject.city.sunset
            )
            WeatherHistory(weatherForecasts = weatherObject.list)
        }
    }
}


