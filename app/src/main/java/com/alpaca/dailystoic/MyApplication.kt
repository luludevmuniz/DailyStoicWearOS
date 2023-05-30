package com.alpaca.dailystoic

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.BackoffPolicy
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.alpaca.dailystoic.data.workers.GetDailyQuoteWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        scheduleDailyQuoteWorker()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    private fun scheduleDailyQuoteWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicRequest = PeriodicWorkRequestBuilder<GetDailyQuoteWorker>(1, TimeUnit.DAYS)
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                backoffDelay = 15,
                timeUnit = TimeUnit.SECONDS
            )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "DialyStoicQuoteWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }
}