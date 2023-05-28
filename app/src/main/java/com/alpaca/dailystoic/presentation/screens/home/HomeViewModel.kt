package com.alpaca.dailystoic.presentation.screens.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.use_cases.UseCases
import com.alpaca.dailystoic.util.Constants.ACTION_QUOTE_UPDATED
import com.alpaca.dailystoic.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("UnspecifiedRegisterReceiverFlag")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases, private val application: Application
) : AndroidViewModel(application = application) {

    private val _dailyQuote = MutableStateFlow<RequestState<Quote>>(value = RequestState.Loading)
    val dailyQuote: StateFlow<RequestState<Quote>> = _dailyQuote

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            fetchDailyQuote()
        }
    }

    init {
        val filter = IntentFilter().apply {
            addAction(ACTION_QUOTE_UPDATED)
        }
        application.baseContext.registerReceiver(receiver, filter)
        fetchDailyQuote()
    }

    private fun fetchDailyQuote() {
        viewModelScope.launch {
            try {
                Log.d("ninja", "fetchDailyQuote")
                useCases.getDailyQuoteUseCase().collect { dailyQuote ->
                    dailyQuote?.let { _dailyQuote.value = RequestState.Success(it) }
                }
            } catch (e: Exception) {
                _dailyQuote.value = (RequestState.Error(e))
            }
        }
    }

    fun updateFavoriteStatus(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.updateFavoriteStatusUseCase(quote = quote.copy(favorite = !quote.favorite))
        }
    }

    override fun onCleared() {
        application.baseContext.unregisterReceiver(receiver)
        super.onCleared()
    }
}