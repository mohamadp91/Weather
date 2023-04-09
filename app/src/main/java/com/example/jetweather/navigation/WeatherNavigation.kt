package com.example.jetweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.screens.about.AboutScreen
import com.example.jetweather.screens.favorites.FavoriteScreen
import com.example.jetweather.screens.main.MainScreen
import com.example.jetweather.screens.search.SearchScreen
import com.example.jetweather.screens.settings.SettingsScreen
import com.example.jetweather.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(WeatherScreens.MainScreen.name + "/{cityName}") {
            val cityName = it.arguments?.getString("cityName") ?: "Tehran"
            MainScreen(navController = navController, cityName = cityName)
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.FavoritesScreen.name) {
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.SettingScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}