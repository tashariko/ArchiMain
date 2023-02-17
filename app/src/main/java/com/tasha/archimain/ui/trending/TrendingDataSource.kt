package com.tasha.archimain.ui.trending

import com.tasha.archimain.data.source.local.dao.TrendingItemDao
import com.tasha.archimain.data.source.local.entity.LoTrendingItem
import com.tasha.archimain.data.source.remote.MiscApiService
import com.tasha.archimain.data.source.remote.response.ReTrendingItem
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
     suspend fun saveList(trendingItems: ArrayList<ReTrendingItem>) {

         //trendingItemDao.insertAll(LoTrendingItem.convertToLocal(trendingItems))
    }

    fun getItems(): Flow<ArrayList<ReTrendingItem>> {
        return trendingItemDao.getAllItems()
    }
}