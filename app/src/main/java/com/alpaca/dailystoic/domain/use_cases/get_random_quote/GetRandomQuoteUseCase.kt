package com.alpaca.dailystoic.domain.use_cases.get_random_quote

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.model.Quote

class GetRandomQuoteUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Quote = repository.getRandomQuote()
}