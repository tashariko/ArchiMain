package com.tasha.archimain.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.MiscApiService
import com.tasha.archimain.network.BaseRemoteDataSource
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
class LandingRemoteDataSourceTest: BaseRemoteDataSource() {

    private lateinit var landingRemoteDataSource: LandingRemoteDataSource
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
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `network call request and success`() {
        runBlocking {
            enqueueResponse("config-response.json")
            val resultResponse = landingRemoteDataSource.getData()

            val request = mockWebServer.takeRequest()
            MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/3/configuration"))

            assertNotNull(resultResponse)
            MatcherAssert.assertThat(resultResponse.status,CoreMatchers.`is`(ApiResult.Status.SUCCESS))
        }
    }

    @Test
    fun `network call failure`() {
        runBlocking {
            enqueueResponse("config-response.json", responseCode = 400)
            val resultResponse = landingRemoteDataSource.getData()

            assertNotNull(resultResponse)
            MatcherAssert.assertThat(resultResponse.status,CoreMatchers.`is`(ApiResult.Status.ERROR))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap(), responseCode: Int = 200) {
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(
                GetConfigFromFile.getdataAsString(fileName)
            ).setResponseCode(responseCode)
        )
    }
}