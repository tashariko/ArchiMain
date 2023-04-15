package com.tasha.archimain.ui.splash

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(JUnit4::class)
@Config(manifest = Config.NONE)
class LandingViewModelTest {
    private lateinit var repository: FakeLandingRepository
    private lateinit var viewModel: LandingViewModel
    private lateinit var configResponse: ConfigurationResponse

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createService() {
        repository = FakeLandingRepository()
        viewModel = LandingViewModel(repository)
        configResponse = GetConfigFromFile.getDataAsObject("config-response.json")
    }

    @Test
    fun loadingState() = runTest {
        repository.getData().emit(ApiResult.loading())
        MatcherAssert.assertThat(viewModel.configLiveData.value.status, CoreMatchers.`is`(ApiResult.Status.LOADING))
    }

    @Test
    fun successState() = runTest {
        repository.getData().emit(ApiResult.success(configResponse))
        MatcherAssert.assertThat(viewModel.configLiveData.value.status, CoreMatchers.`is`(ApiResult.Status.SUCCESS))
        MatcherAssert.assertThat(viewModel.configLiveData.value.data, CoreMatchers.not(null))
    }

    @Test
    fun errorState() {

    }
}