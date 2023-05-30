package com.alpaca.dailystoic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.alpaca.dailystoic.presentation.components.HorizontalPagerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalPagerScreen(onDismissed = { ActivityCompat.finishAffinity(this) })
        }
    }
}