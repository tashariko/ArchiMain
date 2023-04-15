package com.tasha.archimain.ui.splash

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import kotlinx.coroutines.flow.Flow


interface BaseLandingRepository{
    suspend fun getData(): Flow<ApiResult<ConfigurationResponse>>
}
