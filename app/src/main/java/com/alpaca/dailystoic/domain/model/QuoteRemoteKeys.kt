package com.alpaca.dailystoic.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alpaca.dailystoic.util.Constants.QUOTE_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = QUOTE_REMOTE_KEYS_DATABASE_TABLE)
data class QuoteRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)