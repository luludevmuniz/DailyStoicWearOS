package com.alpaca.dailystoic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    SwipeDismissableNavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
//            HomeScreen()
        }
        composable(route = Screen.Favorites.route){
//            FavoritesScreen()
        }
    }
}
