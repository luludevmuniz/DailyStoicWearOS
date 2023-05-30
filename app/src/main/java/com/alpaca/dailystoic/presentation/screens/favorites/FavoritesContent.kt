package com.alpaca.dailystoic.presentation.screens.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Waves
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.CircularProgressIndicator
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.common.PlaceholderScreen
import com.alpaca.dailystoic.presentation.components.QuoteCard
import com.alpaca.dailystoic.ui.theme.EXTRA_LARGE_PADDING
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.paging.items

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun FavoritesContent(
    scalingLazyListState: ScalingLazyListState,
    quotes: LazyPagingItems<Quote>,
    onFavoriteCardClicked: (Quote) -> Unit = {}
) {
    val result = handlePagingResult(quotes = quotes)
    if (result) {
        ScalingLazyColumn(
            state = scalingLazyListState,
            autoCentering = AutoCenteringParams(0, 84),
            anchorType = ScalingLazyListAnchorType.ItemStart,
            contentPadding = PaddingValues(bottom = EXTRA_LARGE_PADDING)
        ) {
            items(items = quotes, key = { quote ->
                quote.id
            }) { quote ->
                quote?.let {
                    QuoteCard(
                        quote = quote,
                        onClick = onFavoriteCardClicked
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
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                false
            }

            error != null -> {
                PlaceholderScreen(
                    title = stringResource(R.string.error_screen_title),
                    message = error.error.message.orEmpty(),
                    icon = Icons.Outlined.ErrorOutline
                )
                false
            }

            itemCount < 1 -> {
                PlaceholderScreen(
                    title = stringResource(id = R.string.empty_screen_title),
                    message = stringResource(id = R.string.empty_screen_message),
                    icon = Icons.Outlined.Waves
                )
                false
            }

            else -> true
        }
    }
}