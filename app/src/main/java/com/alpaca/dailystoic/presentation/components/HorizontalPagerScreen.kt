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
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.alpaca.dailystoic.presentation.screens.favorites.FavoritesScreen
import com.alpaca.dailystoic.presentation.screens.home.HomeScreen
import com.alpaca.dailystoic.util.Constants

@Composable
fun HorizontalPagerScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        vignette = {
            Vignette(vignettePosition = VignettePosition.Top)
        }
    )
    {
        HorizontalPagerContent()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerContent() {
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
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(state = pagerState) { page ->
            selectedPage = pagerState.currentPage
            when (page) {
                0 -> HomeScreen()
                1 -> FavoritesScreen()
            }
        }
        HorizontalPageIndicator(pageIndicatorState = pageIndicatorState)
    }
}
