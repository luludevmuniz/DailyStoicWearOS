package com.alpaca.dailystoic.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.alpaca.dailystoic.domain.model.QuoteRemoteKeys

//@Dao
//interface QuoteRemoteKeysDao {
//    @Query("SELECT * FROM quote_remote_keys_table WHERE id = :quoteId")
//    suspend fun getRemoteKeys(quoteId: Int): QuoteRemoteKeys?
//    @Query("DELETE FROM quote_remote_keys_table")
//    suspend fun deleteAllRemoteKeys()
//
//}