package com.alpaca.dailystoic.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpaca.dailystoic.domain.model.Quote
import com.alpaca.dailystoic.domain.use_cases.UseCases
import com.alpaca.dailystoic.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    private val _randomQuote = MutableStateFlow<RequestState<Quote>>(value = RequestState.Idle)
    val randomQuote: StateFlow<RequestState<Quote>> = _randomQuote

    init {
        viewModelScope.launch {
            _randomQuote.value = RequestState.Loading
            useCases.getRandomQuoteUseCase().collect { quote ->
                _randomQuote.value = RequestState.Success(data = quote)
            }
        }
    }
}