package com.example.androidappmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidappmodule.ui.theme.AndroidAppModuleTheme
import android.content.Intent
import android.util.Log
import android.widget.Button
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.tasks.OnSuccessListener
import com.google.android.play.core.tasks.Task
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private var btnOnDemand: Button? = null
    private var splitInstallManager: SplitInstallManager? = null

    companion object {
        private const val TAG = "HiepTV"
        private const val DYNAMIC_FEATURE_NAME = "super_app"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main)
        initViews()
        setOnClick()
    }

    private fun initViews() {
        btnOnDemand = this.findViewById(R.id.btnOnDemand)
        splitInstallManager = SplitInstallManagerFactory.create(this)
    }

    private fun setOnClick() {
        btnOnDemand?.setOnClickListener {
            if (splitInstallManager?.installedModules?.contains(DYNAMIC_FEATURE_NAME) == true) {
                launchSuperAppFeature()
            } else {
                startDownloading()
            }
        }
    }

    private fun launchSuperAppFeature() {
        val intent = Intent()
        intent.setClassName(
            BuildConfig.APPLICATION_ID, "com.example.super_app.OnDemandActivity"
        )
        startActivity(intent)
    }

    private fun startDownloading() {
        val request = SplitInstallRequest.newBuilder().addModule(DYNAMIC_FEATURE_NAME).build()

        val installTask: Task<Int> = splitInstallManager!!.startInstall(request)

        installTask.addOnSuccessListener(OnSuccessListener { sessionId ->
            // Installation started successfully, monitor the update
            monitorInstallProgress(sessionId)
        })
    }

    private fun monitorInstallProgress(sessionId: Int) {
        // Add a listener to monitor the installation progress
        splitInstallManager?.registerListener { state ->
            // Handle the state update during the installation process
            when (state.status()) {
                SplitInstallSessionStatus.PENDING -> {
                    // Installation is pending
                    Log.d(TAG, "monitorInstallProgress PENDING ")
                }

                SplitInstallSessionStatus.DOWNLOADING -> {
                    // Downloading the module
                    Log.d(TAG, "monitorInstallProgress Downloading ")
                }

                SplitInstallSessionStatus.INSTALLING -> {
                    // Installing the module
                    Log.d(TAG, "monitorInstallProgress INSTALLING ")
                }

                SplitInstallSessionStatus.INSTALLED -> {
                    // Installation is complete, the module is ready to use
                    Log.d(TAG, "monitorInstallProgress INSTALLED ")
                    launchSuperAppFeature()
                }

                SplitInstallSessionStatus.FAILED -> {
                    // Installation failed
                    Log.e(
                        TAG,
                        "monitorInstallProgress Installation FAILED reason ${state.errorCode()}"
                    )
                }

            }

        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidAppModuleTheme {
        Greeting("Android")
    }
}