package com.tasha.archimain.ui.trending

import com.tasha.archimain.data.source.local.dao.TrendingItemDao
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.data.source.remote.MiscApiService
import com.tasha.archimain.network.BaseRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

val DEFAULT_TRENING_PAGE_SIZE = 100

class TrendingRemoteDataSource @Inject constructor(private val service: MiscApiService) :
    BaseRemoteDataSource() {

    suspend fun getMovieData(page: Int) = getResult {
        service.getTrendingMovieItems(page, DEFAULT_TRENING_PAGE_SIZE)
    }

    suspend fun getTvData(page: Int) = getResult {
        service.getTrendingTvItems(page, DEFAULT_TRENING_PAGE_SIZE)
    }
}

class TrendingLocalDataSource @Inject constructor(private val trendingItemDao: TrendingItemDao) {
     suspend fun saveList(trendingItems: List<TrendingItem>) {
         trendingItemDao.insertAll(trendingItems)
    }

    fun getItems(): Flow<List<TrendingItem>> {
        return trendingItemDao.getAllItems()
    }
}