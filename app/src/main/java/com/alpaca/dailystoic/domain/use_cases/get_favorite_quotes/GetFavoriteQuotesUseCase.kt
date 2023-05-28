package com.alpaca.dailystoic.domain.use_cases.get_favorite_quotes

import androidx.paging.PagingData
import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.model.Quote
import kotlinx.coroutines.flow.Flow

class GetFavoriteQuotesUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Quote>> = repository.getFavoriteQuotes()
}