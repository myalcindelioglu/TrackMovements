package com.myd.trackmovements

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.LocationResult

/**
 * Created by MYD on 7/7/18.
 */
class LocationUpdatesBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (ACTION_LOCATION_UPDATES == intent?.action) {
            if (LocationResult.hasResult(intent)) {
                val locationResult = LocationResult.extractResult(intent)
                Log.d(TAG, locationResult.toString())
            }
        }
    }

    companion object {
        const val TAG = "LUBroadcastReceiver"
        const val ACTION_LOCATION_UPDATES = "com.myd.trackmovements.action.ACTION_LOCATION_UPDATES"
    }
}