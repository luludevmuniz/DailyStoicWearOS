package com.alpaca.dailystoic.data.remote

import com.alpaca.dailystoic.domain.model.Quote
import retrofit2.http.GET

interface StoicApi {
    @GET("stoic-quote")
    suspend fun getRandomQuote(): Quote
}