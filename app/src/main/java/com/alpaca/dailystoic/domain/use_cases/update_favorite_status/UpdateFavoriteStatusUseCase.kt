package com.alpaca.dailystoic.domain.use_cases.update_favorite_status

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.model.Quote

class UpdateFavoriteStatusUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(quote: Quote) {
        repository.updateFavoriteStatus(quote = quote)
    }
}