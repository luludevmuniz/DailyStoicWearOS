package com.alpaca.dailystoic.data.repository

import com.alpaca.dailystoic.data.remote.StoicApi
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.repository.RemoteDataSource

class RemoteDataSourceImpl(
    private val stoicApi: StoicApi
) : RemoteDataSource {

    override suspend fun getRandomQuote(): Quote {
        return stoicApi.getRandomQuote()
    }
}