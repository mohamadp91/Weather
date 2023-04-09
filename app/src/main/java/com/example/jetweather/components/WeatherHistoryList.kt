package com.example.jetweather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.models.WeatherForecast
import com.example.jetweather.widgets.WeatherDetailsCard

@Composable
fun WeatherHistory(weatherForecasts: List<WeatherForecast>) {
    Column(
        modifier = Modifier.padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This Week", modifier = Modifier.padding(top = 3.dp))
        WeatherHistoryList(weatherForecast = weatherForecasts)
    }
}

@Composable
fun WeatherHistoryList(weatherForecast: List<WeatherForecast>) {
    LazyColumn(Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {
        items(items = weatherForecast) { item ->
            WeatherDetailsCard(weatherForecast = item)
        }
    }
}