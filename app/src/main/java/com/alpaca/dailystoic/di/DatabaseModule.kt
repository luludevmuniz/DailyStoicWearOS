package com.alpaca.dailystoic.di

import android.content.Context
import androidx.room.Room
import com.alpaca.dailystoic.data.local.StoicDatabase
import com.alpaca.dailystoic.data.repository.LocalDataSourceImpl
import com.alpaca.dailystoic.domain.repository.LocalDataSource
import com.alpaca.dailystoic.util.Constants.STOIC_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): StoicDatabase = Room.databaseBuilder(
        context,
        StoicDatabase::class.java,
        STOIC_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(database: StoicDatabase): LocalDataSource =
        LocalDataSourceImpl(stoicDatabase = database)


}