package com.example.jetweather.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.widgets.WeatherTopBar

@Composable
fun SettingsScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val unit = settingViewModel.unit.collectAsState().value

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
            UnitSettings(unit = unit,
                onSubmitClicked = { navController.popBackStack() })
        }
    }
}

@Composable
fun UnitSettings(
    unit: String,
    onSubmitClicked: () -> Unit,
    settingViewModel: SettingViewModel = hiltViewModel()
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedUnit by remember {
        mutableStateOf(unit)
    }

    Column(verticalArrangement = Arrangement.Top) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = findKeyByValue(selectedUnit) ?: UNIT_MAP.keys.first(),
            )
            Icon(
                imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier.clickable {
                    expanded = true
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                UNIT_MAP.keys.forEach { selectionOption ->
                    DropdownMenuItem(text = {
                        Text(text = selectionOption)
                    },
                        onClick = {
                            selectedUnit = UNIT_MAP.getValue(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
        Divider(modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                saveUnitSettings(selectedUnit, settingViewModel)
                onSubmitClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = Color.Green,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

}

private fun saveUnitSettings(
    selectedUnit: String,
    settingViewModel: SettingViewModel
) {
    settingViewModel.writeUnit(selectedUnit)
}

private val UNIT_MAP = mapOf(
    "Celsius °C" to "metric",
    "Fahrenheit °F" to "imperial",
)

fun findKeyByValue(targetValue: String): String? {
    for ((key, value) in UNIT_MAP) {
        if (value == targetValue) {
            return key
        }
    }
    return null
}
