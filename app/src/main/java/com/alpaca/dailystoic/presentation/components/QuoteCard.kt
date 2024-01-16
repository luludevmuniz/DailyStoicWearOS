package com.alpaca.dailystoic.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import androidx.wear.compose.material.ToggleButtonDefaults
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.presentation.components.TestTags.FAVORITE_ICON
import com.alpaca.dailystoic.presentation.components.TestTags.QUOTE_CARD_CIRCULAR_PROGRESS_INDICATOR
import com.alpaca.dailystoic.presentation.components.TestTags.QUOTE_TEXT
import com.alpaca.dailystoic.ui.theme.DailyStoicTheme
import com.alpaca.dailystoic.ui.theme.MEDIUM_PADDING
import com.alpaca.dailystoic.ui.theme.StoicLightGray
import com.alpaca.dailystoic.ui.theme.StoicRed
import com.google.android.horologist.compose.layout.fillMaxRectangle

@Composable
fun QuoteCard(
    quote: Quote,
    onClick: (Quote) -> Unit = {}
) {
    val context = LocalContext.current

    val favoriteIconColor = animateColorAsState(
        targetValue =
        if (quote.favorite) StoicRed
        else StoicLightGray,
        label = "Favorite Icon Color Animation"
    )
    TitleCard(
        modifier = Modifier
            .fillMaxRectangle()
            .padding(
                if (!context.resources.configuration.isScreenRound) MEDIUM_PADDING
                else 0.dp
            )
            .wrapContentHeight(),
        onClick = {
            onClick(quote)
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (quote.quote == "Loading…") {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .testTag(QUOTE_CARD_CIRCULAR_PROGRESS_INDICATOR)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    FavoriteIcon(
                        modifier = Modifier
                            .testTag(FAVORITE_ICON)
                            .align(Alignment.CenterHorizontally)
                            .size(ToggleButtonDefaults.SmallIconSize),
                        isFavorite = quote.favorite,
                        favoriteIconColor = favoriteIconColor.value
                    )
                }
                Text(
                    text = quote.author
                )
            }
        }
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.testTag(QUOTE_TEXT),
            text = quote.quote,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
private fun FavoriteIcon(
    modifier: Modifier, isFavorite: Boolean, favoriteIconColor: Color
) {
    Icon(
        modifier = modifier,
        tint = favoriteIconColor,
        imageVector = if (isFavorite) {
            Icons.Filled.Favorite
        } else {
            Icons.Default.FavoriteBorder
        },
        contentDescription = stringResource(R.string.favorite_icon)
    )
}

@Preview
@Composable
fun QuoteCardPreview() {
    DailyStoicTheme {
        QuoteCard(quote = Quote(author = "Autor", quote = "Loading…"))
    }
}