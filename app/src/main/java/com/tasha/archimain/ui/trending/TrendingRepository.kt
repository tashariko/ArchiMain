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
    fun getData(page: Int) = object : BaseRepository<List<TrendingItem>,TrendingItemResponse>() {
        override fun shouldfetchDataFromDbBeforeNetwork(): Boolean {
           return true
        }

        override fun shouldStoreDataInDbAfterNetwork(): Boolean {
            return true
        }
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