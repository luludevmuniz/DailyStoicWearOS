package com.alpaca.dailystoic.domain.use_cases.save_daily_quote

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.model.Quote

class SaveDailyQuoteUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(quote: Quote) {
        repository.saveDailyQuote(quote = quote)
    }
}