package com.tasha.archimain.ui.splash

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.data.source.remote.MiscApiService
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import com.tasha.archimain.network.BaseRemoteDataSource
import com.tasha.archimain.util.SharedPreferenceHelper
import javax.inject.Inject


class LandingRemoteDataSource @Inject constructor(private val service: MiscApiService): BaseRemoteDataSource() {

    suspend fun getData() = getResult {
        service.configuration()
    }
}

class LandingLocalDataSource @Inject constructor(private val application: Application) {
     fun saveConfiguration(config: ConfigurationResponse) {
         SharedPreferenceHelper.putInSharedPreference(
             application,
             AppConstants.SP_KEY_CONFIG,
             Gson().toJson(config, object: TypeToken<ConfigurationResponse>() {}.type)
         )
    }
}