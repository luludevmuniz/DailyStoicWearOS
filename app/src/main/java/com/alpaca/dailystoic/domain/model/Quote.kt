package com.alpaca.dailystoic.domain.model

@kotlinx.serialization.Serializable
data class Quote(
    val author: String = "",
    val quote: String = ""
)