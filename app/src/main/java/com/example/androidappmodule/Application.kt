package com.example.androidappmodule

import android.app.Application
import android.support.multidex.MultiDex
import com.google.firebase.FirebaseApp
import com.google.firebase.installations.FirebaseInstallations

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        FirebaseApp.initializeApp(this);
        FirebaseInstallations.getInstance()
    }
}