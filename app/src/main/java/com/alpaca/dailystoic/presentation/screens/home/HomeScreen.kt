package com.alpaca.dailystoic.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.alpaca.dailystoic.presentation.screens.QuoteViewModel

@Composable
fun HomeScreen(
    quoteViewModel: QuoteViewModel = hiltViewModel()
) {
    val dailyQuote by quoteViewModel.dailyQuote.collectAsState()
    HomeContent(
        quote = dailyQuote,
        onClickQuote = { quote ->
            quoteViewModel.updateFavoriteStatus(quote = quote)
        }
    )
}