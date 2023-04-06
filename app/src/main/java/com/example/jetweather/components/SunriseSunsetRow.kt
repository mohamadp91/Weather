package com.example.jetweather.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetweather.R
import com.example.jetweather.util.Utils


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
            text = Utils.formatDateTime(timestamp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
