package com.alpaca.dailystoic.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Favorites: Screen("favorites_screen")
}