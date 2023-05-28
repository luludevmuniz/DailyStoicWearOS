package com.alpaca.dailystoic.domain.use_cases

import com.alpaca.dailystoic.domain.use_cases.get_daily_quote.GetDailyQuoteUseCase
import com.alpaca.dailystoic.domain.use_cases.get_favorite_quotes.GetFavoriteQuotesUseCase
import com.alpaca.dailystoic.domain.use_cases.get_random_quote.GetRandomQuoteUseCase
import com.alpaca.dailystoic.domain.use_cases.save_daily_quote.SaveDailyQuoteUseCase
import com.alpaca.dailystoic.domain.use_cases.update_favorite_status.UpdateFavoriteStatusUseCase

data class UseCases(
    val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    val getDailyQuoteUseCase: GetDailyQuoteUseCase,
    val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase,
    val saveDailyQuoteUseCase: SaveDailyQuoteUseCase,
    val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
)