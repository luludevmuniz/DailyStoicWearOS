package com.alpaca.dailystoic.di

import com.alpaca.dailystoic.data.remote.StoicApi
import com.alpaca.dailystoic.data.repository.RemoteDataSourceImpl
import com.alpaca.dailystoic.domain.repository.RemoteDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.JavaNetCookieJar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieManager(): CookieManager = CookieManager()

    @Provides
    @Singleton
    fun provideOkHttpClient(cookieManager: CookieManager): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://api.themotivate365.com")
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideStoicApi(retrofit: Retrofit): StoicApi =
        retrofit.create(StoicApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        stoicApi: StoicApi,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            stoicApi = stoicApi,
        )
    }
}