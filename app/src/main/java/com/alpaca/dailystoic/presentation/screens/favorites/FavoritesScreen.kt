package com.alpaca.dailystoic.presentation.screens.favorites

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert
import androidx.wear.compose.material.dialog.Dialog
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.screens.QuoteViewModel
import com.alpaca.dailystoic.ui.theme.StoicDarkGray

@Composable
fun FavoritesScreen(
    quoteViewModel: QuoteViewModel = hiltViewModel(),
) {
    val quotes = quoteViewModel.favoriteQuotes.collectAsLazyPagingItems()
    quoteViewModel.fetchFavoriteQuotes()
    var showDialog by remember { mutableStateOf(false) }
    var selectedQuote by remember { mutableStateOf(Quote()) }
    val scrollState = rememberScalingLazyListState()

    FavoritesContent(
        quotes = quotes,
        onFavoriteCardClicked = { quote ->
            selectedQuote = quote
            showDialog = true
        }
    )

    Dialog(
        showDialog = showDialog,
        onDismissRequest = { showDialog = false },
        scrollState = scrollState
    ) {
        Alert(title = { Text(text = stringResource(R.string.delete_quote_dialog_title)) },
            content = {
                Text(
                    text = stringResource(R.string.delete_quote_dialog_content),
                    textAlign = TextAlign.Center
                )
            },
            positiveButton = {
                Button(
                    onClick = {
                        showDialog = false
                        quoteViewModel.updateFavoriteStatus(quote = selectedQuote)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(R.string.delete_quote_icon)
                    )
                }
            },
            negativeButton = {
                Button(
                    onClick = {
                        showDialog = false
                    },
                    colors = ButtonDefaults.primaryButtonColors(backgroundColor = StoicDarkGray)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_delete_dialog_icon)
                    )
                }
            }
        )
    }
}