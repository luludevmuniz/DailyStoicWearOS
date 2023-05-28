package com.alpaca.dailystoic.domain.repository

import androidx.paging.PagingData
import com.alpaca.dailystoic.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getDailyQuote(): Flow<Quote?>
    fun getFavoriteQuotes(): Flow<PagingData<Quote>>
    suspend fun saveDailyQuote(quote: Quote)

    suspend fun updateFavoriteStatus(quote: Quote)
}