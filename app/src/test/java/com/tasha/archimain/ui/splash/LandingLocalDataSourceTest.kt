package com.tasha.archimain.ui.splash

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.application.ArchiMainApplication
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import com.tasha.archimain.util.SharedPreferenceHelper
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LandingLocalDataSourceTest {

    private lateinit var context: Context
    private lateinit var localDataSource: LandingLocalDataSource

    @Before
    fun setup() {
        context = getApplicationContext()
        localDataSource = LandingLocalDataSource((context as Application))



        localDataSource.saveConfiguration(
            Gson().fromJson<ConfigurationResponse>(
                GetConfigFromFile.getdataAsString("config-response.json"),
                object : TypeToken<ConfigurationResponse>() {}.type
            ), AppConstants.SP_KEY_CONFIG_TEST
        )
    }

    @After
    fun clearSharedPref() {
        SharedPreferenceHelper.clearSharedPrefForKey(context, AppConstants.SP_KEY_CONFIG_TEST)
    }

    @Test
    fun checkConfig() {
        val configString = SharedPreferenceHelper.getStringFromSharedPreference(
            context,
            AppConstants.SP_KEY_CONFIG_TEST
        )
        val configurationResponse = Gson().fromJson<ConfigurationResponse>(
            configString,
            object : TypeToken<ConfigurationResponse>() {}.type
        )

        assertNotNull(configString)
        MatcherAssert.assertThat(configString, CoreMatchers.not("0"))
        MatcherAssert.assertThat(
            configurationResponse.images.base_url,
            CoreMatchers.containsString("image.tmdb.org")
        )
    }
}