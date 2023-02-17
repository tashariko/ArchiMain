package com.tasha.archimain.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tasha.archimain.data.source.local.entity.LoUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface UserDao {

    //Just returns the user
    @Query("SELECT * FROM `User` WHERE id = :userId")
    fun getUser(userId: Long): Flow<LoUser>

    @Delete
    fun deleteUser(user: LoUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: LoUser)

}
