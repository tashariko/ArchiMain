package com.tasha.archimain.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasha.archimain.data.source.local.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Movie)

    @Query("SELECT * FROM `movie` WHERE id = :id")
    fun getMovie(id: Long): Flow<Movie>

    @Query("DELETE FROM movie")
    suspend fun clearAllItems()

}