package com.alpaca.dailystoic.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alpaca.dailystoic.data.local.StoicDatabase
import com.alpaca.dailystoic.domain.model.Quote
import javax.inject.Inject

class QuotePagingSource @Inject constructor(private val stoicDatabase: StoicDatabase) :
    PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val page = params.key ?: 1
            val quotes = stoicDatabase.quoteDao().getFavoriteQuotesByPage(page)
            val prevPage = if (page > 1) page - 1 else null
            val nextPage = if (quotes.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = quotes,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition
    }
}
