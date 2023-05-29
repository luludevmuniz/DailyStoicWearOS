package com.alpaca.dailystoic.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyListState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    scalingLazyListState: ScalingLazyListState
) {
    val dailyQuote by homeViewModel.dailyQuote.collectAsState()
    HomeContent(
        scalingLazyListState = scalingLazyListState,
        quote = dailyQuote,
        onClickQuote = { quote ->
            homeViewModel.updateFavoriteStatus(quote = quote)
        }
    )
}