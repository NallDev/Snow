package com.nalldev.snow.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.nalldev.snow.R
import java.util.UUID

object Notification {
    fun sendNotification(context: Context, title: String, message: String) {
        try {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.splash_icon)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSubText("INI SUB TEXT")

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
            val notification = builder.build()
            val notificationId = UUID.randomUUID().hashCode()
            notificationManager.notify(notificationId, notification)
            println("NOTIFICATION SEND WITH TITILE : $title")
        } catch (e : Exception) {
            println("NOTIFICATION NOT SEND : ${e.message}")
        }
    }

    const val CHANNEL_ID = "ws_channel"
    const val CHANNEL_NAME = "ws channel"
}