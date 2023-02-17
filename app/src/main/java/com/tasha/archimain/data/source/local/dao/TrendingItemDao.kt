package com.tasha.archimain.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasha.archimain.data.source.local.entity.LoTrendingItem
import com.tasha.archimain.data.source.local.entity.TrendingRemoteKey
import kotlinx.coroutines.flow.Flow


@Dao
interface TrendingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: ArrayList<LoTrendingItem>)

    @Query("SELECT * FROM trending_item")
    fun getAllItems(): Flow<ArrayList<LoTrendingItem>>

    @Query("DELETE FROM trending_item")
    suspend fun clearAllItems()

}

@Dao
interface TrendingRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<TrendingRemoteKey>)

    @Query("SELECT * FROM trending_remote_key WHERE repoId = :id")
    suspend fun remoteKeysTrendingId(id: Long): TrendingRemoteKey?

    @Query("DELETE FROM trending_remote_key")
    suspend fun clearRemoteKeys()
}