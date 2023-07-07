package com.tasha.archimain.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.tasha.archimain.R
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.application.BaseActivity
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.ErrorType
import com.tasha.archimain.databinding.ActivityLandingBinding
import com.tasha.archimain.ui.MainActivity
import com.tasha.archimain.ui.login.LoginActivity
import com.tasha.archimain.ui.parallelflow.ParallelFlowActivity
import com.tasha.archimain.util.SharedPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : BaseActivity() {
    companion object {

        fun launchLandingScreen(context: Context) {
            val mainIntent = Intent(context, LandingActivity::class.java)
            context.startActivity(mainIntent)
            (context as BaseActivity).finish()
        }
    }

    lateinit var binding: ActivityLandingBinding

    private val viewModel: LandingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindAndSetupUI()
        vmListeners()
    }

    override fun handleIncomingIntent() {

    }

    override fun bindAndSetupUI() {
        binding.retryButton.setOnClickListener {
            viewModel.getConfig()
        }
    }

    override fun vmListeners() {
        viewModel.configLiveData.observe(this) {
            binding.progressBar.isVisible = false
            binding.retryButton.isVisible = false
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                ApiResult.Status.SUCCESS -> {
                    if (SharedPreferenceHelper.getBooleanFromSharedPreference(
                            this,
                            AppConstants.SP_IS_LOGGED_IN
                        )
                    ) {
                        if(!SharedPreferenceHelper.getBooleanFromSharedPreference(
                                this,
                                AppConstants.SP_IS_SECOND_FLOW
                            )) {
                            ParallelFlowActivity.launchScreen(this@LandingActivity)
                        }else{
                            MainActivity.launchScreen(this@LandingActivity)
                        }
                    } else {
                        LoginActivity.launchLoginScreen(this@LandingActivity)
                    }
                }
                ApiResult.Status.ERROR -> {
                    it.errorType?.let { et ->
                        if (et.type == ErrorType.Type.Generic) {
                            showToast(getString(R.string.generic_error_message))
                        } else {
                            et.message?.let { msg ->
                                showToast(msg)
                            } ?: run {
                                showToast(getString(R.string.generic_error_message))
                            }
                        }
                    } ?: run {
                        showToast(getString(R.string.generic_error_message))
                    }
                    binding.retryButton.isVisible = true
                }
            }
        }

        viewModel.getConfig()
    }

    override fun viewlisteners() {

    }
}