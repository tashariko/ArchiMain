package com.tasha.archimain.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tasha.archimain.data.source.Converters
import com.tasha.archimain.data.source.local.dao.TrendingItemDao
import com.tasha.archimain.data.source.local.dao.TrendingRemoteKeysDao
import com.tasha.archimain.data.source.local.dao.UserDao
import com.tasha.archimain.data.source.local.entity.*


@Database(
    entities = [User::class, TrendingItem::class, TrendingRemoteKey::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun trendingItemDao(): TrendingItemDao
    abstract fun trendingRemoteKeysDao(): TrendingRemoteKeysDao
}