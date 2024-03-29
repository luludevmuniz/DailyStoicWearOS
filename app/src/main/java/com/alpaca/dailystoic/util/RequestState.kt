package com.alpaca.dailystoic.util

sealed class RequestState<out T> {
    operator fun compareTo(initialState: RequestState<@UnsafeVariance T>): Int {
        return if (this == initialState) {
            1
        } else {
            0
        }
    }

    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()
}