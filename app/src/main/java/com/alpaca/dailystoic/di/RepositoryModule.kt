package com.alpaca.dailystoic.di

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.use_cases.UseCases
import com.alpaca.dailystoic.domain.use_cases.get_daily_quote.GetDailyQuoteUseCase
import com.alpaca.dailystoic.domain.use_cases.get_favorite_quotes.GetFavoriteQuotesUseCase
import com.alpaca.dailystoic.domain.use_cases.get_random_quote.GetRandomQuoteUseCase
import com.alpaca.dailystoic.domain.use_cases.save_daily_quote.SaveDailyQuoteUseCase
import com.alpaca.dailystoic.domain.use_cases.update_favorite_status.UpdateFavoriteStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getRandomQuoteUseCase = GetRandomQuoteUseCase(repository = repository),
            getDailyQuoteUseCase = GetDailyQuoteUseCase(repository = repository),
            getFavoriteQuotesUseCase = GetFavoriteQuotesUseCase(repository = repository),
            updateFavoriteStatusUseCase = UpdateFavoriteStatusUseCase(repository = repository),
            saveDailyQuoteUseCase = SaveDailyQuoteUseCase(repository = repository)
        )
    }
}