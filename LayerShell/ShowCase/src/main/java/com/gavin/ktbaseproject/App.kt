package com.gavin.ktbaseproject

import android.app.Application
import android.content.Context
import com.gavin.common.ApplicationGlobal
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun attachBaseContext(base: Context) {
        ApplicationGlobal.applicationContext = base
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
    }

}