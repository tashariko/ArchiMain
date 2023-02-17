package com.tasha.archimain.data.source.local.dao

import androidx.room.*
import com.tasha.archimain.data.source.local.entity.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    //Just returns the user
    @Query("SELECT * FROM `User` WHERE id = :userId")
    fun getUser(userId: Long): Flow<User>

    @Delete
    fun deleteUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

}
