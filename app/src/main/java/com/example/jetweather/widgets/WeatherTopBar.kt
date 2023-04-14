package com.example.jetweather.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.components.MoreDropDownMenu
import com.example.jetweather.models.FavoriteModel
import com.example.jetweather.navigation.WeatherScreens
import com.example.jetweather.screens.favorites.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    title: String,
    isMainScreen: Boolean,
    onButtonClicked: () -> Unit,
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val shouldOpenMenu = remember {
        mutableStateOf(false)
    }
    val showIt = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

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
            .shadow(6.dp),
        actions = {
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
                    tint = Color.Gray
                )
            } else {
                val dataList = title.split("/")
                val favoriteModel = FavoriteModel(dataList[0], dataList[1])
                val isFavorite = remember {
                    mutableStateOf(false)
                }
                isFavorite.value = favoritesViewModel.isFavorite(favoriteModel.city)

                AddFavorite(isFavorite) { value ->
                    isFavorite.value = value
                    if (value) {
                        favoritesViewModel.insertFavorite(favoriteModel).run {
                            showIt.value = true
                        }
                    } else {
                        favoritesViewModel.deleteFavorite(favoriteModel)
                    }
                }
                ShowFavoriteToast(context = context, showIt = showIt)
            }
        })
}

@Composable
fun AddFavorite(
    isFavorite: MutableState<Boolean>,
    onCheckedChange: (Boolean) -> Unit
) {
    IconButton(onClick = {
        onCheckedChange(!isFavorite.value)
    }) {
        Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "",
            tint = if (isFavorite.value) Color.Red.copy(alpha = .8f) else Color.LightGray,
            modifier = Modifier.scale(.9f)
        )
    }
}

@Composable
fun ShowFavoriteToast(context: Context, showIt: MutableState<Boolean>) {
    if (showIt.value) {
        Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
        showIt.value = false
    }
}