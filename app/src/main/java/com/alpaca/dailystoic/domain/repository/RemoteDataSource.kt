package com.alpaca.dailystoic.domain.repository

import com.alpaca.dailystoic.domain.model.Quote

interface RemoteDataSource {
    suspend fun getRandomQuote(): Quote
}