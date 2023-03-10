package com.tasha.archimain.ui.trending

import com.tasha.archimain.data.source.local.dao.TrendingItemDao
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.data.source.remote.MiscApiService
import com.tasha.archimain.network.BaseRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

val DEFAULT_TRENIND_PAGE_SIZE = 20

class TrendingRemoteDataSource @Inject constructor(private val service: MiscApiService) :
    BaseRemoteDataSource() {

    suspend fun getData(page: Int) = getResult {
        service.getTrendingItems(page, DEFAULT_TRENIND_PAGE_SIZE)
    }
}

class TrendingLocalDataSource @Inject constructor(private val trendingItemDao: TrendingItemDao) {
     suspend fun saveList(trendingItems: ArrayList<TrendingItem>) {

         trendingItemDao.insertAll(trendingItems)
    }

    fun getItems(): Flow<ArrayList<TrendingItem>> {
        return trendingItemDao.getAllItems()
    }
}