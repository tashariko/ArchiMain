package com.tasha.archimain.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.tasha.archimain.BuildConfig
import com.tasha.archimain.R
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.application.BaseActivity
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.User
import com.tasha.archimain.databinding.ActivityLoginBinding
import com.tasha.archimain.ui.MainActivity
import com.tasha.archimain.util.UtilityHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    companion object {
        fun launchLoginScreen(context: Context) {
            val mainIntent = Intent(context, LoginActivity::class.java)
            context.startActivity(mainIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindAndSetupUI()
    }

    override fun bindAndSetupUI() {
        binding.btnSend.setOnClickListener {
            with(binding.etUsername.text.toString().trim()) {
                if(isNullOrEmpty()){
                    showToast(getString(R.string.label_provide_username))
                }else{
                    viewModel.createUser(this, viewModel.getLanguage(this@LoginActivity, binding.rgLanguage))
                }
            }
        }

        viewModel.createTaskLiveData.observe(this, Observer { item ->
            when (item.status) {
                ApiResult.Status.LOADING -> if (item.data == null) {
                    configureView(AppConstants.LOADING_LAYOUT, AppConstants.VIEW_FROM_LOADING)
                } else {
                    configureView(AppConstants.DATA_LAYOUT, AppConstants.VIEW_FROM_LOADING)
                    updateUI(item.data)
                }
                ApiResult.Status.SUCCESS -> {
                    updateUI(item.data!!)

                    configureView(AppConstants.DATA_LAYOUT, AppConstants.VIEW_FROM_API)
                }
                ApiResult.Status.ERROR -> if (!UtilityHelper.showDataInError()) {
                    configureView(AppConstants.ERROR_LAYOUT, AppConstants.VIEW_FROM_ERROR)
                } else {
                    if (item.data == null) {
                        configureView(AppConstants.ERROR_LAYOUT, AppConstants.VIEW_FROM_ERROR)
                    } else {
                        configureView(AppConstants.DATA_LAYOUT, AppConstants.VIEW_FROM_ERROR)
                        updateUI(item.data)
                    }
                }
                else -> {

                }
            }
        })
    }

    private fun updateUI(data: User) {
        if(BuildConfig.DEBUG) showToast("Logged in as ${data.name}")

        MainActivity.launchScreen(this)
    }

    private fun configureView(loadingLayout: String, viewFromLoading: String) {
        showToast("$loadingLayout : $viewFromLoading")
    }

    override fun handleIncomingIntent() {
        TODO("Not yet implemented")
    }

    override fun vmListeners() {
        TODO("Not yet implemented")
    }

    override fun viewlisteners() {
        TODO("Not yet implemented")
    }
}