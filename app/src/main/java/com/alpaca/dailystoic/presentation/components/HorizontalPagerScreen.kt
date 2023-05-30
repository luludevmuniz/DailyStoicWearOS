package com.alpaca.dailystoic.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.SwipeToDismissBoxState
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.edgeSwipeToDismiss
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import com.alpaca.dailystoic.presentation.screens.favorites.FavoritesScreen
import com.alpaca.dailystoic.presentation.screens.home.HomeScreen
import com.alpaca.dailystoic.ui.theme.DialyStoicTheme
import com.alpaca.dailystoic.util.Constants

@Composable
fun HorizontalPagerScreen(onDismissed: () -> Unit = {}) {
    DialyStoicTheme {
        val scalingLazyListState =
            rememberScalingLazyListState(
                initialCenterItemIndex = 0,
                initialCenterItemScrollOffset = 90
            )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            positionIndicator = {
                PositionIndicator(scalingLazyListState = scalingLazyListState)
            },
            vignette = {
                Vignette(vignettePosition = VignettePosition.Top)
            })
        {
            val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
            SwipeToDismissBox(
                state = swipeToDismissBoxState,
                onDismissed = onDismissed
            ) { isBackground ->
                if (isBackground) {
                    Box(modifier = Modifier.fillMaxSize())
                } else {
                    HorizonalPagerContent(
                        swipeToDismissBoxState = swipeToDismissBoxState,
                        scalingLazyListState = scalingLazyListState
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizonalPagerContent(
    swipeToDismissBoxState: SwipeToDismissBoxState,
    scalingLazyListState: ScalingLazyListState
) {
    var selectedPage by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { Constants.MAX_PAGES }
    val pageIndicatorState: PageIndicatorState = remember {
        object : PageIndicatorState {
            override val pageOffset: Float
                get() = Constants.PAGE_INDICATOR_OFFSET
            override val selectedPage: Int
                get() = selectedPage
            override val pageCount: Int
                get() = Constants.MAX_PAGES
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .edgeSwipeToDismiss(swipeToDismissBoxState),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(state = pagerState) { page ->
            selectedPage = pagerState.currentPage
            when (page) {
                0 -> HomeScreen(scalingLazyListState = scalingLazyListState)
                1 -> FavoritesScreen(scalingLazyListState = scalingLazyListState)
            }
        }
        HorizontalPageIndicator(pageIndicatorState = pageIndicatorState)
    }
}
