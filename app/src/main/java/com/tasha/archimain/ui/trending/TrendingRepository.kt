package com.tasha.archimain.ui.trending

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.data.source.remote.response.TrendingItemResponse
import com.tasha.archimain.network.BaseRepository
import javax.inject.Inject

class TrendingRepository  @Inject constructor(
    private val localDataSource: TrendingLocalDataSource,
    private val remoteDataSource: TrendingRemoteDataSource
) {
    fun getData(page: Int) = object : BaseRepository<ArrayList<TrendingItem>,TrendingItemResponse>() {
    }.repoWork(
        databaseQuery = {
            null
        },
        networkCall = {
            remoteDataSource.getData(page)
        },
        saveCallResult = {
            localDataSource.saveList(it.results)
        },
        parseNetworkResponse = {
            ApiResult.success(it.results)
        }
    )
}