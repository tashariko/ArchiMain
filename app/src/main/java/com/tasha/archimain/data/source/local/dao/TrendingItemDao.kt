package com.tasha.archimain.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.data.source.local.entity.TrendingRemoteKey
import kotlinx.coroutines.flow.Flow


@Dao
interface TrendingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: ArrayList<TrendingItem>)

    @Query("SELECT * FROM trending_item")
    fun getAllItems(): Flow<ArrayList<TrendingItem>>

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