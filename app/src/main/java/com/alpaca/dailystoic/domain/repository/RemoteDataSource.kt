package com.alpaca.dailystoic.domain.repository

import com.alpaca.dailystoic.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getRandomQuote(): Flow<Quote>
}