package com.alpaca.dailystoic.domain.use_cases.get_daily_quote

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.model.Quote
import kotlinx.coroutines.flow.Flow

class GetDailyQuoteUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Quote?> = repository.getDailyQuote()
}