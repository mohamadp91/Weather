package com.example.jetweather.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherTopBar(
            title = "Setting",
            isMainScreen = false,
            onButtonClicked = { navController.popBackStack() },
            navController = navController
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            UnitSettings()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitSettings() {

    val units = listOf("Celsius °C", "Fahrenheit °F")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(units[0]) }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Text(
            text = selectedOptionText,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Icon(
            imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier.clickable {
                expanded = true
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            units.forEach { selectionOption ->
                DropdownMenuItem(text = {
                    Text(text = selectionOption)
                },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}