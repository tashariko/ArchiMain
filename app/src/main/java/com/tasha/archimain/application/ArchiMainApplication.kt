package com.tasha.archimain.application

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArchiMainApplication : MultiDexApplication() {

    override fun onCreate() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .detectNetwork()   // or .detectAll() for all detectable problems
            .penaltyLog()
            .penaltyDialog()
            .build())
        super.onCreate()
    }
}