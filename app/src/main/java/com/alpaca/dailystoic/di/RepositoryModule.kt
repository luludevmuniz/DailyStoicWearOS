package com.alpaca.dailystoic.di

import com.alpaca.dailystoic.data.repository.Repository
import com.alpaca.dailystoic.domain.use_cases.UseCases
import com.alpaca.dailystoic.domain.use_cases.get_random_quote.GetRandomQuoteUseCase
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
            getRandomQuoteUseCase = GetRandomQuoteUseCase(repository = repository)
        )
    }

}