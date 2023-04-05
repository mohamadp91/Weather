package com.example.jetweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetweather.models.WeatherForecast
import com.example.jetweather.ui.theme.DarkYellow
import com.example.jetweather.ui.theme.Purple40
import com.example.jetweather.util.IMAGE_BASE_URL
import com.example.jetweather.util.Utils.formatDate
import com.example.jetweather.util.Utils.formatDecimal

@Composable
fun WeatherDetailsCard(weatherForecast: WeatherForecast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(
            topStartPercent = 25,
            bottomEndPercent = 25,
            bottomStartPercent = 25
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        DetailsRow(weatherForecast)
    }
}

@Composable
fun DetailsRow(weatherForecast: WeatherForecast) {
    val weatherCondition = weatherForecast.weather[0]
    val model = "$IMAGE_BASE_URL/img/wn/${weatherCondition.icon}.png"
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formatDate(weatherForecast.dt).split(",")[0],
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        AsyncImage(
            model = model,
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = weatherCondition.main,
            modifier = Modifier
                .background(
                    color = Color(DarkYellow),
                    shape = RoundedCornerShape(percent = 25)
                )
                .padding(8.dp)
        )
        val annotatedString = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                withStyle(style = SpanStyle(color = Purple40)) {
                    append("${formatDecimal(weatherForecast.main.temp_min)}°  ")
                }
                withStyle(SpanStyle(color = Color.Red)) {
                    append("${formatDecimal(weatherForecast.main.temp_max)}°")
                }
            }
        }
        Text(annotatedString)
    }
}