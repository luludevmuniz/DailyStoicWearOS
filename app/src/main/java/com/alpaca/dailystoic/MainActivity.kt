package com.alpaca.dailystoic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.alpaca.dailystoic.navigation.SetupNavGraph
import com.alpaca.dailystoic.ui.theme.DialyStoicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialyStoicTheme {
                navController = rememberSwipeDismissableNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}