package com.myd.trackmovements

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices


/**
 * Created by MYD on 7/7/18.
 */
class ForegroundTrackService : Service() {

    lateinit var locationResult: LocationRequest

    override fun onCreate() {
        super.onCreate()
        locationResult = LocationRequest()
        locationResult.interval = Constants.LOCATION_UPDATE_INTERVAL
        locationResult.fastestInterval = Constants.FASTEST_LOCATION_UPDATE_INTERVAL
        locationResult.maxWaitTime = Constants.MAX_LOCATION_UPDATE_WAIT_TIME
        locationResult.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        requestLocationUpdates()
        startForeground(Constants.FOREGROUND_NOTIFICATION_ID, createOngoingNotification())
        return START_STICKY
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationResult, getPendingIntent())
    }

    private fun createOngoingNotification(): Notification {

        return NotificationHelper(this).getForegroundNotification().build()
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, LocationUpdatesBroadcastReceiver::class.java)
        intent.action = LocationUpdatesBroadcastReceiver.ACTION_LOCATION_UPDATES
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        removeLocationUpdates()
    }

    private fun removeLocationUpdates() {
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(getPendingIntent())
    }

}