package com.nalldev.snow.presentation.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.nalldev.snow.R

object Notification {
    fun sendNotification(context: Context, title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.splash_icon)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSubText("SUB TEXT")

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        builder.setChannelId(CHANNEL_ID)
        notificationManager.createNotificationChannel(channel)
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    const val NOTIFICATION_ID = 1
    const val CHANNEL_ID = "channel_01"
    const val CHANNEL_NAME = "dicoding channel"
}