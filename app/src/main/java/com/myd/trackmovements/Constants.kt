package com.myd.trackmovements

/**
 * Created by MYD on 7/7/18.
 */
class Constants {
    companion object {
        const val LOCATION_UPDATE_INTERVAL: Long = 10 * 1000
        const val FASTEST_LOCATION_UPDATE_INTERVAL: Long = LOCATION_UPDATE_INTERVAL / 2
        const val MAX_LOCATION_UPDATE_WAIT_TIME: Long = LOCATION_UPDATE_INTERVAL * 3

        const val FOREGROUND_NOTIFICATION_ID = 1111
    }
}