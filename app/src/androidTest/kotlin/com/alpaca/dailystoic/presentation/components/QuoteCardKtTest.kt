package com.alpaca.dailystoic.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alpaca.dailystoic.presentation.components.TestTags.CIRCULAR_PROGRESS_INDICATOR
import com.alpaca.dailystoic.presentation.components.TestTags.FAVORITE_ICON
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class QuoteCardKtTest
{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingStateShowsLoadingCard() {
        composeTestRule.setContent {
            QuoteCard(autor = "Wait", citacao = "Loading...")
        }
        composeTestRule.onNodeWithTag(CIRCULAR_PROGRESS_INDICATOR).assertIsDisplayed()
        composeTestRule.onNodeWithTag(FAVORITE_ICON).assertDoesNotExist()
    }

    @Test
    fun succesStateShowsCompleteCard() {
        composeTestRule.setContent {
            QuoteCard(autor = "Seneca", citacao = "It is the power of the mind to be unconquerable")
        }
        composeTestRule.onNodeWithTag(CIRCULAR_PROGRESS_INDICATOR).assertDoesNotExist()
        composeTestRule.onNodeWithTag(FAVORITE_ICON, useUnmergedTree = true).assertIsDisplayed()
    }
}