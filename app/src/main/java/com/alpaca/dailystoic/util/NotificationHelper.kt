package com.alpaca.dailystoic.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.alpaca.dailystoic.R
import com.alpaca.dailystoic.domain.model.Quote

class NotificationHelper(private val context: Context) {
    private val notificationManager = NotificationManagerCompat.from(context)

    @SuppressLint("MissingPermission")
    fun sendPushNotification(dailyQuote: Quote) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val notificationBuilder = buildNotification(dailyQuote)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermissions()) {
            // TODO: Request notification permissions
        } else {
            notificationManager.notify(dailyQuote.id, notificationBuilder)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channelId = "Daily Stoic Quotes"
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        notificationManager.createNotificationChannel(channel)
    }

    private fun buildNotification(randomQuote: Quote): Notification {
        val contentTitle = randomQuote.author
        val contentText = randomQuote.author
        val bigTextStyle = Notification.BigTextStyle().bigText(randomQuote.quote)
        val builder =
            Notification.Builder(context).setContentTitle(contentTitle).setContentText(contentText)
                .setStyle(bigTextStyle)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("Daily Stoic Quotes")
        }

        return builder.build()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasNotificationPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }
}
