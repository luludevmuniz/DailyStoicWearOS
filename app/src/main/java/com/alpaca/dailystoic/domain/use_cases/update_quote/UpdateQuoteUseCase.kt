package com.alpaca.dailystoic.domain.use_cases.update_quote

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.model.Quote

class UpdateQuoteUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(quote: Quote) {
        repository.updateQuote(quote = quote)
    }
}