package com.tasha.archimain.ui.splash

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeLandingRepository : BaseLandingRepository {
    private val fakeFlow = MutableSharedFlow<ApiResult<ConfigurationResponse>>()
    override suspend fun getData() = fakeFlow
}