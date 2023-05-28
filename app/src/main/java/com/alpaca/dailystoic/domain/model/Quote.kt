package com.alpaca.dailystoic.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alpaca.dailystoic.util.Constants.QUOTE_TABLE

@kotlinx.serialization.Serializable
@Entity(tableName = QUOTE_TABLE)
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String = "",
    val quote: String = "",
    val favorite: Boolean = false
)