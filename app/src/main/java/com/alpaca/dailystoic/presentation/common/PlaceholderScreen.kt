package com.alpaca.dailystoic.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.ui.theme.MEDIUM_PADDING

@Composable
fun PlaceholderScreen(
    title: String,
    message: String,
    icon: ImageVector
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(MEDIUM_PADDING)
        ) {
            Image(
                imageVector = icon,
                contentDescription = stringResource(R.string.empty_screen_icon_image_description),
                modifier = Modifier.size(32.dp),
                colorFilter = ColorFilter.tint(color = Color.White)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.title3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}