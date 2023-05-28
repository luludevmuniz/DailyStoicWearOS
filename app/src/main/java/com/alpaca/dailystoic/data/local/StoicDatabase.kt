package com.alpaca.dailystoic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alpaca.dailystoic.data.local.dao.QuoteDao
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.model.QuoteRemoteKeys

@Database(entities = [Quote::class, QuoteRemoteKeys::class], version = 1, exportSchema = false)
abstract class StoicDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
//    abstract fun quoteRemoteKeysDao(): QuoteRemoteKeysDao
}