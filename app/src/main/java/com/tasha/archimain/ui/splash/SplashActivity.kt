package com.tasha.archimain.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tasha.archimain.application.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    /**
     * Bug:
     * Not going fullscreen
     */

    companion object {
        fun relaunchApp(context: Context) {
            val mainIntent = Intent(context, SplashActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(mainIntent)
            (context as BaseActivity).finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LandingActivity.launchLandingScreen(this)
    }

    override fun handleIncomingIntent() {

    }

    override fun bindAndSetupUI() {

    }

    override fun vmListeners() {

    }

    override fun viewlisteners() {

    }
}