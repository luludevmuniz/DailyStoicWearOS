package com.alpaca.dailystoic.data.workers

import android.content.Context
import android.content.Intent
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.alpaca.dailystoic.domain.use_cases.UseCases
import com.alpaca.dailystoic.util.Constants.ACTION_QUOTE_UPDATED
import com.alpaca.dailystoic.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.runBlocking

@HiltWorker
class GetDailyQuoteWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val useCases: UseCases

) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        var result: Result
        runBlocking {
            result = try {
                val randomQuote = useCases.getRandomQuoteUseCase()
                if (randomQuote.quote.isNotEmpty() && randomQuote.author.isNotEmpty()) {
                    useCases.saveDailyQuoteUseCase(quote = randomQuote)
                    val intent = Intent(ACTION_QUOTE_UPDATED)
                    applicationContext.sendBroadcast(intent)
                    NotificationHelper(context = appContext).sendPushNotification(dailyQuote = randomQuote)
                    Result.success()
                } else {
                    Result.failure()
                }
            } catch (e: Exception) {
                Result.failure()
            }
        }
        return result
    }
}
