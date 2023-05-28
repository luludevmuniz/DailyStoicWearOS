package com.alpaca.dailystoic.presentation.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListAnchorType
import androidx.wear.compose.material.ScalingLazyListState
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.components.QuoteCard
import com.alpaca.dailystoic.ui.theme.EXTRA_LARGE_PADDING
import com.alpaca.dailystoic.util.RequestState

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun HomeContent(
    scalingLazyListState: ScalingLazyListState,
    quote: RequestState<Quote>,
    onClickFavoriteQuote: (Quote) -> Unit
) {
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
                when (quote) {
                    is RequestState.Success -> {
                        QuoteCard(
                            quote = quote.data,
                            onClickFavoriteQuote = onClickFavoriteQuote
                        )
                    }
                    is RequestState.Loading -> {
                        QuoteCard(
                            quote = Quote(
                                author = stringResource(R.string.wait),
                                quote = stringResource(R.string.loading)
                            )
                        )
                    }
                    is RequestState.Error -> {}
                    RequestState.Idle -> {}
                }
            }
        }
    }
}