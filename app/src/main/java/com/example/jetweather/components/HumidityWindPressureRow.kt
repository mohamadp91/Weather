package com.example.jetweather.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetweather.R
import com.example.jetweather.models.WeatherForecast
import com.example.jetweather.util.Utils

@Composable
fun HumidityWindPressureRow(weatherForecast: WeatherForecast) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        HumidityWindPressureItem(
            painter = painterResource(id = R.drawable.sun_and_cloud_png_hd),
            sign = "%",
            text = weatherForecast.main.humidity.toString()
        )
        HumidityWindPressureItem(
            painter = painterResource(id = R.drawable.sun_and_cloud_png_hd),
            sign = "mph",
            text = Utils.formatDecimal(weatherForecast.wind.speed)
        )
        HumidityWindPressureItem(
            painter = painterResource(id = R.drawable.sun_and_cloud_png_hd),
            sign = "psi",
            text = weatherForecast.main.pressure.toString()
        )
    }
}

@Composable
fun HumidityWindPressureItem(
    painter: Painter,
    text: String,
    sign: String
) {
    Row(horizontalArrangement = Arrangement.Start) {
        Icon(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
        )
        Text(text = "$text $sign")
    }
}