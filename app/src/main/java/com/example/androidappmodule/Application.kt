package com.example.androidappmodule

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.installations.installations
import com.google.firebase.installations.ktx.installations
import com.google.firebase.ktx.Firebase


class MyApplication : Application() {
//    override fun attachBaseContext(context: Context?) {
//        super.attachBaseContext(context)
//
//    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        FirebaseApp.initializeApp(this);
        FirebaseInstallations.getInstance()
    }
}