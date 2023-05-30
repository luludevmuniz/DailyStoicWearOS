package com.alpaca.dailystoic.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alpaca.dailystoic.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table ORDER BY ID DESC LIMIT 1")
    fun getDailyQuote(): Flow<Quote>

    @Query("SELECT * FROM quote_table WHERE favorite = 1 ORDER BY id ASC LIMIT :pageSize OFFSET (:page - 1) * :pageSize")
    suspend fun getFavoriteQuotesByPage(page: Int, pageSize: Int = 10): List<Quote>

    @Query("DELETE FROM quote_table WHERE favorite = 0")
    suspend fun deleteNonFavoriteQuotes()


    @Update
    suspend fun updateQuote(quote: Quote)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDailyQuote(quote: Quote)
}