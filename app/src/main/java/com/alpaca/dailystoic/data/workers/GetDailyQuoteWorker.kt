package com.alpaca.dailystoic.data.workers

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alpaca.dailystoic.domain.use_cases.UseCases
import com.alpaca.dailystoic.util.Constants.ACTION_QUOTE_UPDATED
import com.alpaca.dailystoic.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GetDailyQuoteWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val useCases: UseCases

) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val result: Result = try {
            Log.d("BRITNEY", "início")
            val randomQuote = useCases.getRandomQuoteUseCase()
            if (randomQuote.quote.isNotEmpty() && randomQuote.author.isNotEmpty()) {
                useCases.saveDailyQuoteUseCase(quote = randomQuote)
                val intent = Intent(ACTION_QUOTE_UPDATED)
                applicationContext.sendBroadcast(intent)
                NotificationHelper(context = appContext).sendPushNotification(dailyQuote = randomQuote)
                Log.d("BRITNEY", "sucesso")
                Result.success()
            } else {
                Log.d("BRITNEY", "retry")
                Result.retry()
            }
        } catch (e: Exception) {
            Log.d("BRITNEY", "error: $e")
            Result.failure()
        }
        return result
    }
}
