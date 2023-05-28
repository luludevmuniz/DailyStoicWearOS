package com.alpaca.dailystoic.presentation.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import com.alpaca.dailystoic.ui.theme.DialyStoicTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val quote by homeViewModel.dailyQuote.collectAsState()

    val scalingLazyListState =
        rememberScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 90
        )

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
            HomeContent(
                scalingLazyListState = scalingLazyListState,
                quote = quote,
                onClickFavoriteQuote = { quote ->
                    homeViewModel.updateFavoriteStatus(quote = quote)
                }
            )
        }
    )
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DialyStoicTheme {
        HomeScreen()
    }
}