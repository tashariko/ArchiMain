package com.tasha.archimain.application

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArchiMainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}