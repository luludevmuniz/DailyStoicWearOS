package com.alpaca.dailystoic.presentation.screens.home

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.PositionIndicator
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.common.PlaceholderScreen
import com.alpaca.dailystoic.presentation.components.QuoteCard
import com.alpaca.dailystoic.util.RequestState
import kotlinx.coroutines.launch

@Composable
fun HomeContent(
    quote: RequestState<Quote>,
    onClickQuote: (Quote) -> Unit
) {
    when (quote) {
        is RequestState.Success -> {
            val scrollState = rememberScrollState()
            val focusRequester = remember { FocusRequester() }
            val coroutineScope = rememberCoroutineScope()
            Box {
                PositionIndicator(scrollState = scrollState)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .onRotaryScrollEvent {
                            coroutineScope.launch {
                                scrollState.scrollBy(it.verticalScrollPixels)
                                scrollState.animateScrollBy(0f)
                            }
                            true
                        }
                        .focusRequester(focusRequester)
                        .focusable(),
                    verticalArrangement = Arrangement.Center
                ) {
                    QuoteCard(
                        quote = quote.data,
                        onClick = onClickQuote
                    )
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
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