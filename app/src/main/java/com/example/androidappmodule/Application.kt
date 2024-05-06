package com.example.androidappmodule

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication


class MyApplication : MultiDexApplication() {
    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}