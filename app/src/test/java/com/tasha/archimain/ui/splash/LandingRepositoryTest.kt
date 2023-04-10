package com.tasha.archimain.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.tasha.archimain.data.source.remote.MiscApiService
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
class LandingRepositoryTest {
    private lateinit var landingRemoteDataSource: LandingRemoteDataSource
    private lateinit var landingLocalDataSource: LandingLocalDataSource

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: MiscApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MiscApiService::class.java)
        landingRemoteDataSource = LandingRemoteDataSource(service)
        landingLocalDataSource = LandingLocalDataSource(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `network call`() {

    }

    @Test
    fun `save call result`() {

    }

    @Test
    fun `parse success response`() {

    }
}