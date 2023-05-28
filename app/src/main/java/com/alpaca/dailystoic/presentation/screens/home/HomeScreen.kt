package com.alpaca.dailystoic.presentation.screens.home

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListAnchorType
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.presentation.components.QuoteCard
import com.alpaca.dailystoic.ui.theme.EXTRA_LARGE_PADDING
import com.alpaca.dailystoic.ui.theme.DialyStoicTheme
import com.alpaca.dailystoic.util.RequestState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val quote by homeViewModel.dailyQuote.collectAsState()
    Log.d("LENINJA", "HomeScreen quote = $quote")

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
            ScalingLazyColumn(
                state = scalingLazyListState,
                autoCentering = AutoCenteringParams(0, 90),
                anchorType = ScalingLazyListAnchorType.ItemStart,
                contentPadding = PaddingValues(bottom = EXTRA_LARGE_PADDING)
            )
            {

                item {
                    AnimatedContent(
                        targetState = quote,
                        label = stringResource(R.string.sliding_card_animation),
                        transitionSpec = {
                            if (targetState.compareTo(initialState) == 1) {
                                slideInHorizontally { height -> height } + fadeIn() with
                                        slideOutHorizontally { height -> -height } + fadeOut()
                            } else {
                                slideInHorizontally { height -> -height } + fadeIn() with
                                        slideOutHorizontally { height -> height } + fadeOut()
                            }.using(
                                SizeTransform(clip = false)
                            )
                        }
                    )
                    {
                        when(val requestedQuote = quote) {
                            is RequestState.Success -> {
                                Log.d("LENINJA", "HomeScreen RequestState.Success $requestedQuote")
                                QuoteCard(
                                    autor = requestedQuote.data.author,
                                    citacao = requestedQuote.data.quote
                                )
                            }
                            is RequestState.Loading -> {
                                QuoteCard(
                                    autor = stringResource(R.string.wait),
                                    citacao = stringResource(R.string.loading)
                                )                        }
                            is RequestState.Error -> {}
                            RequestState.Idle -> {}
                        }
                    }
                }
            }
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