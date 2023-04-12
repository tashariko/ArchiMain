package com.tasha.archimain.ui.splash

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.MiscApiService
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
class LandingViewModelTest {
    private lateinit var context: Context
    private lateinit var landingRemoteDataSource: LandingRemoteDataSource
    private lateinit var landingLocalDataSource: LandingLocalDataSource
    private lateinit var repository: LandingRepository
    private lateinit var viewModel: LandingViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: MiscApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        context = ApplicationProvider.getApplicationContext()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MiscApiService::class.java)
        landingRemoteDataSource = LandingRemoteDataSource(service)
        landingLocalDataSource = LandingLocalDataSource(context as Application)
        repository = LandingRepository(landingRemoteDataSource,landingLocalDataSource)
        viewModel = LandingViewModel(repository)
    }

    @Test
    fun loadingState() {
        //viewModel.configLiveData.value = A
    }

    @Test
    fun successState() {

    }

    @Test
    fun errorState() {

    }
}