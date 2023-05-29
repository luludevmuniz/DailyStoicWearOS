package com.alpaca.dailystoic.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import com.alpaca.dailystoic.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val favoriteQuotes = useCases.getFavoriteQuotesUseCase()
}
