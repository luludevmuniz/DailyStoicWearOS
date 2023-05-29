package com.alpaca.dailystoic.presentation.screens.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.CircularProgressIndicator
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.common.EmptyScreen
import com.alpaca.dailystoic.presentation.components.QuoteCard
import com.alpaca.dailystoic.ui.theme.EXTRA_LARGE_PADDING
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.paging.items

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun FavoritesContent(
    scalingLazyListState: ScalingLazyListState,
    quotes: LazyPagingItems<Quote>
) {
    val result = handlePagingResult(quotes = quotes)

    if (result) {
        ScalingLazyColumn(
            state = scalingLazyListState,
            autoCentering = AutoCenteringParams(0, 84),
            anchorType = ScalingLazyListAnchorType.ItemStart,
            contentPadding = PaddingValues(bottom = EXTRA_LARGE_PADDING)
//            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(items = quotes, key = { quote ->
                quote.id
            }) { quote ->
                quote?.let {
                    QuoteCard(
                        quote = quote,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    quotes: LazyPagingItems<Quote>
): Boolean {
    quotes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when {
            loadState.refresh is LoadState.Loading -> {
                CircularProgressIndicator()
                false
            }

            error != null -> {
//                EmptyScreen(
//                    error = error, heroes = heroes
//                )
                EmptyScreen()
                false
            }

            itemCount < 1 -> {
                EmptyScreen()
                false
            }

            else -> true
        }
    }
}