package com.alpaca.dailystoic.presentation.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    scalingLazyListState: ScalingLazyListState
) {
    val quotes = favoritesViewModel.favoriteQuotes.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        positionIndicator = {
            if (scalingLazyListState.isScrollInProgress) {
                PositionIndicator(scalingLazyListState = scalingLazyListState)
            }
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        content =
        {
            FavoritesContent(
                quotes = quotes,
                scalingLazyListState = scalingLazyListState
            )
        }
    )
}