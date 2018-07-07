package com.myd.trackmovements

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val intentForeground = Intent(this, ForegroundTrackService::class.java)
        if (intent.action == ACTION_STOP) {
            stopService(intentForeground)
        } else {
            startService(intentForeground)
        }
    }

    companion object {
        const val ACTION_STOP = "com.myd.trackmovements.action.ACTION_STOP"
    }
}
