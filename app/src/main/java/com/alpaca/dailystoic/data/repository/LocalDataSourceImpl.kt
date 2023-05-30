package com.alpaca.dailystoic.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alpaca.dailystoic.data.local.StoicDatabase
import com.alpaca.dailystoic.data.paging_source.QuotePagingSource
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val stoicDatabase: StoicDatabase) :
    LocalDataSource {
    private val quoteDao = stoicDatabase.quoteDao()

    override fun getDailyQuote(): Flow<Quote> = quoteDao.getDailyQuote()

    override fun getFavoriteQuotes(): Flow<PagingData<Quote>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { QuotePagingSource(stoicDatabase) }
        ).flow
    }

    override suspend fun saveDailyQuote(quote: Quote) {
        quoteDao.deleteNonFavoriteQuotes()
        quoteDao.saveDailyQuote(quote = quote)
    }

    override suspend fun updateQuote(quote: Quote) =
        quoteDao.updateQuote(quote = quote)
}