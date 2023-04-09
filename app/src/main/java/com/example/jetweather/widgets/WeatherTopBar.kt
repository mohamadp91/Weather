package com.example.jetweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetweather.components.MoreDropDownMenu
import com.example.jetweather.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    title: String,
    isMainScreen: Boolean,
    onButtonClicked: () -> Unit,
    navController: NavController,
) {
    val shouldOpenMenu = remember {
        mutableStateOf(false)
    }
    TopAppBar(title = {
        Text(
            text = title,
            color = Color.DarkGray,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        )
    },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .shadow(6.dp), actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
                IconButton(onClick = {
                    shouldOpenMenu.value = true
                }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
                }
                MoreDropDownMenu(shouldMenuOpen = shouldOpenMenu, navController = navController)
            } else {
                Box {}
            }

        }, navigationIcon = {
            if (!isMainScreen) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.clickable { onButtonClicked.invoke() },
                    tint = Color.DarkGray
                )
            }
        })
}