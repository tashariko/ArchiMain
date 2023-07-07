package com.tasha.archimain.ui.mainflow.movie

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.data.source.remote.response.TrendingItemResponse
import com.tasha.archimain.network.BaseRepository
import com.tasha.archimain.ui.mainflow.trending.TrendingLocalDataSource
import com.tasha.archimain.ui.mainflow.trending.TrendingRemoteDataSource
import javax.inject.Inject


class MovieRepository  @Inject constructor(
    private val localDataSource: TrendingLocalDataSource,
    private val remoteDataSource: TrendingRemoteDataSource
) {
    fun getMovieData(page: Int) = object : BaseRepository<List<TrendingItem>, TrendingItemResponse>() {
        override fun shouldfetchDataFromDbBeforeNetwork(): Boolean {
            return true
        }

        override fun shouldStoreDataInDbAfterNetwork(): Boolean {
            return true
        }
    }.repoWork(
        databaseQuery = {
            localDataSource.getItems()
        },
        networkCall = {
            remoteDataSource.getMovieData(page)
        },
        saveCallResult = {
            localDataSource.saveList(it.results)
        },
        parseNetworkResponse = {
            ApiResult.success(it.results)
        }
    )
}