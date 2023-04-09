package com.example.jetweather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetweather.models.WeatherForecast
import com.example.jetweather.ui.theme.DarkYellow
import com.example.jetweather.util.IMAGE_BASE_URL
import com.example.jetweather.util.Utils


@Composable
fun TopContent(weatherForecast: WeatherForecast) {
    val currentCondition = weatherForecast.weather[0]
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = Utils.formatDate(weatherForecast.dt),
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 7.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Column(
            modifier = Modifier
                .size(170.dp)
                .background(
                    shape = CircleShape,
                    color = Color(DarkYellow)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val model = "$IMAGE_BASE_URL/img/wn/${currentCondition.icon}.png"
            AsyncImage(
                model = model,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 4.dp)
            )
            Text(
                text = Utils.formatDecimal(weatherForecast.main.temp) + "°",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = currentCondition.main,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                ),
            )
        }
    }
}
