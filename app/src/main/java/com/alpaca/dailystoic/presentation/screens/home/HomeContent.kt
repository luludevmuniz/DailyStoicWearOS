package com.alpaca.dailystoic.presentation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.common.PlaceholderScreen
import com.alpaca.dailystoic.presentation.components.QuoteCard
import com.alpaca.dailystoic.ui.theme.EXTRA_LARGE_PADDING
import com.alpaca.dailystoic.util.RequestState

@Composable
fun HomeContent(
    quote: RequestState<Quote>,
    onClickQuote: (Quote) -> Unit
) {
    val scalingLazyListState =
        rememberScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 90
        )
    ScalingLazyColumn(
        state = scalingLazyListState,
        autoCentering = AutoCenteringParams(0, 90),
        anchorType = ScalingLazyListAnchorType.ItemStart,
        contentPadding = PaddingValues(bottom = EXTRA_LARGE_PADDING)
    )
    {
        item {
            when (quote) {
                is RequestState.Success -> {
                    QuoteCard(
                        quote = quote.data,
                        onClick = onClickQuote
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

                is RequestState.Error -> {
                    PlaceholderScreen(
                        title = stringResource(R.string.error_screen_title),
                        message = quote.error.message.orEmpty(),
                        icon = Icons.Outlined.ErrorOutline
                    )
                }

                RequestState.Idle -> {}
            }
        }
    }
}