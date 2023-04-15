package com.tasha.archimain.ui.splash

import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import com.tasha.archimain.network.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LandingRepository @Inject constructor(
    private val landingRemoteDataSource: LandingRemoteDataSource,
    private val landingLocalDataSource: LandingLocalDataSource
): BaseLandingRepository {
    override suspend fun getData() = object : BaseRepository<ConfigurationResponse,ConfigurationResponse>() {
        override fun shouldfetchDataFromDbBeforeNetwork() = false
    }.repoWork(
        databaseQuery = {
            null
        },
        networkCall = {
            landingRemoteDataSource.getData()
        },
        saveCallResult = {
            landingLocalDataSource.saveConfiguration(it, AppConstants.SP_KEY_CONFIG)
        },
        parseNetworkResponse = {
            ApiResult.success(it)
        }
    )
}
