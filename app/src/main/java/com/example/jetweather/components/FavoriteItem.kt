package com.example.jetweather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetweather.models.FavoriteModel
import com.example.jetweather.navigation.WeatherScreens

@Composable
fun FavoriteItem(
    favoriteModel: FavoriteModel,
    navController: NavController,
    onDelete: (city: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .height(50.dp)
            .background(
                color = Color.Cyan.copy(alpha = .5f),
                shape = RoundedCornerShape(
                    topStartPercent = 25,
                    bottomStartPercent = 25,
                    bottomEndPercent = 25
                )
            )
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favoriteModel.city}")
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = favoriteModel.city,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 18.sp
        )
        Text(
            text = favoriteModel.country,
            color = Color.Black.copy(.8f),
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(percent = 25)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp), fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { onDelete(favoriteModel.city) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                tint = Color.Red.copy(alpha = 0.7f),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}