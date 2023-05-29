package com.alpaca.dailystoic.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val StoicBlue = Color(0xFF1565C0)
val StoicDarkBlue = Color(0xFF0D47A1)
val StoicTeal = Color(0xFF00897B)
val StoicLightGray = Color(0xFFB0BEC5)
val StoicDarkGray = Color(0xFF455A64)
val StoicRed = Color(0xFFE57373)


internal val wearColorPalette: Colors = Colors(
    primary = StoicBlue,
    primaryVariant = StoicDarkBlue,
    secondary = StoicTeal,
    secondaryVariant = StoicTeal,
    error = StoicRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onError = Color.Black
)