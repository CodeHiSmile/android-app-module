package com.example.super_app

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity

//import io.flutter.embedding.android.FlutterActivity

class OnDemandActivity : AppCompatActivity() {
    private var btnOnDemand: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_demand)
        val intent = Intent (this, FlutterActivity::class.java)
        askNotificationPermission()
        btnOnDemand = this.findViewById(R.id.tvClick)

        btnOnDemand!!.setOnClickListener {
            startActivity(
            FlutterActivity.createDefaultIntent(this)
            )
        }
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, "PERMISSION_GRANTED") ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale("POST_NOTIFICATIONS")) {
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch("POST_NOTIFICATIONS")
            }
        }
    }

}
