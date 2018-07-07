package com.myd.trackmovements

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat


/**
 * Created by MYD on 7/7/18.
 */
class NotificationHelper(context: Context) : ContextWrapper(context) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan1 = NotificationChannel(PRIMARY_CHANNEL,
                    getString(R.string.notification_channel_default), NotificationManager.IMPORTANCE_DEFAULT)
            chan1.lightColor = Color.GREEN
            chan1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            getManager().createNotificationChannel(chan1)

            val chan2 = NotificationChannel(SECONDARY_CHANNEL,
                    getString(R.string.notification_channel_second), NotificationManager.IMPORTANCE_HIGH)
            chan2.lightColor = Color.BLUE
            chan2.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            getManager().createNotificationChannel(chan2)
        }
    }

    private fun getManager(): NotificationManager {
        return getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun getForegroundNotification(): NotificationCompat.Builder {
        val tapIntent = Intent(baseContext, MainActivity::class.java)
        val tapPendingIntent = PendingIntent.getActivity(baseContext,
                0, tapIntent,
                0)

        val stopIntent = Intent(baseContext, MainActivity::class.java)
        stopIntent.action = MainActivity.ACTION_STOP
        stopIntent.putExtra("stop", true)
        val stopPendingIntent = PendingIntent.getActivity(baseContext,
                1, stopIntent,
                0)


        return NotificationCompat.Builder(baseContext, PRIMARY_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.foreground_notification_content_text))
                .setContentIntent(tapPendingIntent)
                .addAction(android.R.drawable.ic_media_pause,
                        getString(R.string.foreground_notification_stop_text), stopPendingIntent)


    }


    companion object {
        const val PRIMARY_CHANNEL = "default"
        const val SECONDARY_CHANNEL = "second"
    }

}