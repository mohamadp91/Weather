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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    title: String,
    isMainScreen: Boolean,
    onAddActionClicked: () -> Unit,
    onButtonClicked: () -> Unit,
    navController: NavController,
) {
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
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
                }
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