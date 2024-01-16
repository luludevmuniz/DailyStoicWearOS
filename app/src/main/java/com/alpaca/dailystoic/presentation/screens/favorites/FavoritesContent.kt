package com.alpaca.dailystoic.presentation.screens.favorites

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Waves
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.PositionIndicator
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.common.PlaceholderScreen
import com.alpaca.dailystoic.presentation.components.QuoteCard
import kotlinx.coroutines.launch

@Composable
fun FavoritesContent(
    quotes: LazyPagingItems<Quote>,
    onFavoriteCardClicked: (Quote) -> Unit = {}
) {
    val result = handlePagingResult(quotes = quotes)
    val lazyListState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()

    if (result) {
        Box {
            PositionIndicator(lazyListState = lazyListState)
            LazyColumn(
                modifier = Modifier
                    .onRotaryScrollEvent {
                        coroutineScope.launch {
                            lazyListState.scrollBy(it.verticalScrollPixels)
                            lazyListState.animateScrollBy(0f)
                        }
                        true
                    }
                    .focusRequester(focusRequester)
                    .focusable(),
                state = lazyListState
            ) {
                items(
                    count = quotes.itemCount,
                    key = quotes.itemKey { it.id },
                    contentType = quotes.itemContentType { it }
                ) { index ->
                    val quote = quotes[index]
                    if (quote != null) {
                        QuoteCard(
                            quote = quote,
                            onClick = onFavoriteCardClicked
                        )
                    }
                }
            }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
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