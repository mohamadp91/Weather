package com.example.jetweather.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.navigation.WeatherScreens

@Composable
fun MoreDropDownMenu(
    shouldMenuOpen: MutableState<Boolean>,
    navController: NavController
) {
    DropdownMenu(
        expanded = shouldMenuOpen.value,
        modifier = Modifier
            .wrapContentHeight(),
        onDismissRequest = {
            shouldMenuOpen.value = false
        }) {
        MoreMenuItem(
            icon = Icons.Rounded.Favorite,
            title = "Favorites"
        ) {
            navController.navigate(WeatherScreens.FavoritesScreen.name)
        }
        MoreMenuItem(
            icon = Icons.Rounded.Settings,
            title = "Setting"
        ) {
            navController.navigate(WeatherScreens.SettingScreen.name)
        }
        MoreMenuItem(
            icon = Icons.Rounded.Info,
            title = "About"
        ) {
            navController.navigate(WeatherScreens.AboutScreen.name)
        }
    }
}

@Composable
fun MoreMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(120.dp)
            .height(30.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier.padding(start = 4.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}