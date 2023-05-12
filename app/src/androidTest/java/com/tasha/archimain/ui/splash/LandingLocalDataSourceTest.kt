package com.tasha.archimain.ui.splash

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
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
//
//@RunWith(AndroidJUnit4::class)
//class LandingLocalDataSourceTest {
//
//    private lateinit var context: Context
//    private lateinit var localDataSource: LandingLocalDataSource
//
//    @Before
//    fun setup() {
//        context = getApplicationContext<ArchiMainApplication>()
//        localDataSource = LandingLocalDataSource((context as ArchiMainApplication?)!!)
//
//        val inputStream = javaClass.classLoader
//            .getResourceAsStream("api-response/config-response.json")
//        val configString = inputStream.source().buffer().readString(Charsets.UTF_8)
//
//        localDataSource.saveConfiguration(Gson().fromJson<ConfigurationResponse>(configString, object: TypeToken<ConfigurationResponse>() {}.type),AppConstants.SP_KEY_CONFIG_TEST)
//    }
//
//    @After
//    fun clearSharedPref(){
//        SharedPreferenceHelper.clearSharedPrefForKey(context, AppConstants.SP_KEY_CONFIG_TEST)
//    }
//
//    @Test
//    fun checkConfig(){
//        val configString = SharedPreferenceHelper.getStringFromSharedPreference(context, AppConstants.SP_KEY_CONFIG_TEST)
//        val configurationResponse = Gson().fromJson<ConfigurationResponse>(configString, object: TypeToken<ConfigurationResponse>() {}.type)
//
//        assertNotNull(configString)
//        MatcherAssert.assertThat(configString, CoreMatchers.not("0"))
//        MatcherAssert.assertThat(configurationResponse.images.base_url,CoreMatchers.containsString("image.tmdb.org"))
//    }
//
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals(
//            "com.tasha.archimain", appContext.packageName
//        )
//    }
//
//}