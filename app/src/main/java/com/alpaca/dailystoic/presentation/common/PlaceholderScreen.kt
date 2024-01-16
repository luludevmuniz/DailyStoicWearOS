package com.alpaca.dailystoic.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Text
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.ui.theme.MEDIUM_PADDING
import kotlinx.coroutines.launch

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
        val scrollState = rememberScrollState()
        val focusRequester = remember { FocusRequester() }
        val coroutineScope = rememberCoroutineScope()
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
                .focusable()
                .padding(MEDIUM_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally
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