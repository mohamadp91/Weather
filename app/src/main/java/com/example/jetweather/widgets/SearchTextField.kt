package com.example.jetweather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    onSearch: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchQueryState = remember {
        mutableStateOf("")
    }
    val isSearchQueryValid = remember(
        searchQueryState.value,
        searchQueryState.value.trim()::isNotEmpty
    )

    OutlinedTextField(
        value = searchQueryState.value,
        onValueChange = { searchQueryState.value = it },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        if (isSearchQueryValid) {
                            onSearch(searchQueryState.value.trim())
                            searchQueryState.value = ""
                            keyboardController?.hide()
                        }
                    }
            )
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(percent = 25),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent, cursorColor = Color.Black
        ),
        label = {
            Text(
                "Spokane",
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions {
            if (!isSearchQueryValid)
                return@KeyboardActions
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyboardController?.hide()
        }
    )
}