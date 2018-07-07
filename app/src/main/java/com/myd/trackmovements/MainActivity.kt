package com.myd.trackmovements

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import permissions.dispatcher.*


@RuntimePermissions
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
            startTrackingServiceWithPermissionCheck(intentForeground)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun startTrackingService(intent: Intent) {
        startService(intent)
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showLocationDenied() {
        Toast.makeText(this, R.string.permission_fine_location_denied, Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForLocation(request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setMessage(R.string.permission_fine_location_rationale)
                .setPositiveButton(R.string.button_allow, { dialog, button -> request.proceed() })
                .setNegativeButton(R.string.button_deny, { dialog, button -> request.cancel() })
                .show()
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showNeverAskForLocation() {
        Toast.makeText(this, R.string.permission_fine_location_neverask, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ACTION_STOP = "com.myd.trackmovements.action.ACTION_STOP"
    }
}
