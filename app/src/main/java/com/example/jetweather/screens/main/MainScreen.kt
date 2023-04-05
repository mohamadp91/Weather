package com.example.jetweather.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jetweather.R
import com.example.jetweather.data.ResultState
import com.example.jetweather.models.WeatherForecast
import com.example.jetweather.models.WeatherObject
import com.example.jetweather.ui.theme.DarkYellow
import com.example.jetweather.util.IMAGE_BASE_URL
import com.example.jetweather.util.Utils.formatDate
import com.example.jetweather.util.Utils.formatDateTime
import com.example.jetweather.util.Utils.formatDecimal
import com.example.jetweather.widgets.WeatherDetailsCard
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val result =
            produceState<ResultState<WeatherObject>>(
                initialValue = ResultState.Loading,
                producer = {
                    value = mainViewModel.getWeather("moscow", "imperial")
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
            onAddActionClicked = {},
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
            Column(
                modifier = Modifier.padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "This Week", modifier = Modifier.padding(top = 3.dp))
                WeatherHistoryList(weatherForecast = weatherObject.list)
            }
        }
    }
}

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
            text = formatDate(weatherForecast.dt),
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
                text = formatDecimal(weatherForecast.main.temp) + "°",
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
            text = formatDecimal(weatherForecast.wind.speed)
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

@Composable
fun SunriseSunsetRow(sunrise: Int, sunset: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        SunriseSunsetItem(timestamp = sunrise)
        SunriseSunsetItem(timestamp = sunset)
    }
}

@Composable
fun SunriseSunsetItem(
    timestamp: Int,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.sun_and_cloud_png_hd),
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = formatDateTime(timestamp),
            style = MaterialTheme.typography.bodyMedium
        )
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


