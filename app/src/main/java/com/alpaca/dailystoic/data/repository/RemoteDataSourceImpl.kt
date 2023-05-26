package com.alpaca.dailystoic.data.repository

import com.alpaca.dailystoic.data.remote.StoicismQuoteApi
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(
    private val stoicApi: StoicismQuoteApi
) : RemoteDataSource {

    override suspend fun getRandomQuote(): Flow<Quote> {
        return flowOf(stoicApi.getRandomQuote())
    }
}