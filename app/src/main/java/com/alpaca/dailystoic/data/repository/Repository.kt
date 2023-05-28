package com.alpaca.dailystoic.data.repository

import androidx.paging.PagingData
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.repository.LocalDataSource
import com.alpaca.dailystoic.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
) {
    suspend fun getRandomQuote(): Quote = remote.getRandomQuote()

    fun getDailyQuote(): Flow<Quote?> = local.getDailyQuote()

    fun getFavoriteQuotes(): Flow<PagingData<Quote>> = local.getFavoriteQuotes()

    suspend fun saveDailyQuote(quote: Quote) = local.saveDailyQuote(quote = quote)
    suspend fun updateFavoriteStatus(quote: Quote) = local.updateFavoriteStatus(quote = quote)

}