package com.alpaca.dailystoic.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import androidx.wear.compose.material.ToggleButtonDefaults
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.presentation.components.TestTags.CIRCULAR_PROGRESS_INDICATOR
import com.alpaca.dailystoic.presentation.components.TestTags.FAVORITE_ICON
import com.alpaca.dailystoic.presentation.components.TestTags.QUOTE_TEXT
import com.alpaca.dailystoic.ui.theme.DialyStoicTheme
import com.alpaca.dailystoic.ui.theme.StoicLightGray
import com.alpaca.dailystoic.ui.theme.StoicRed

@Composable
fun QuoteCard(autor: String, citacao: String) {
    var isFavorite by remember { mutableStateOf(false) }
    val favoriteIconColor = remember { Animatable(initialValue = StoicLightGray) }
    LaunchedEffect(isFavorite) {
        favoriteIconColor.animateTo(targetValue = if (isFavorite) StoicRed else StoicLightGray)
    }
    TitleCard(
        onClick = { isFavorite = !isFavorite },
        title = { Text(text = autor) },
        time = {
            if (citacao == "Loading…") {
                CircularProgressIndicator(
                    modifier = Modifier.testTag(CIRCULAR_PROGRESS_INDICATOR)
                )
            } else {
                FavoriteIcon(
                    modifier = Modifier
                        .size(ToggleButtonDefaults.SmallIconSize)
                        .testTag(FAVORITE_ICON),
                    isFavorite = isFavorite,
                    favoriteIconColor = favoriteIconColor.value
                )
            }
        }
    ) {
        Text(
            modifier = Modifier.testTag(QUOTE_TEXT),
            text = citacao
        )
    }

}

@Composable
fun FavoriteIcon(
    modifier: Modifier,
    isFavorite: Boolean,
    favoriteIconColor: Color
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
    DialyStoicTheme {
        QuoteCard("Autor", "Citacao")
    }
}