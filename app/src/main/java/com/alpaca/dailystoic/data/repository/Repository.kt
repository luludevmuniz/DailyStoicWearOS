package com.alpaca.dailystoic.data.repository

import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource
) {
    suspend fun getRandomQuote(): Flow<Quote> = remote.getRandomQuote()
}