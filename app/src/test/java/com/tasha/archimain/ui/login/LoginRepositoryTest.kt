package com.tasha.archimain.ui.login

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.USER_LANGUAGE
import com.tasha.archimain.data.source.local.entity.User
import com.tasha.archimain.network.BaseRepository
import org.junit.Assert.*
import org.junit.Test

class LoginRepositoryTest {

    @Test
    fun createUser()  = object : BaseRepository<User, User>() {
        override fun shouldfetchDataFromDbBeforeNetwork() = false
    }.repoWork(
        databaseQuery = {

            null
        },
        networkCall = {
            var userName = "User"
            var language = USER_LANGUAGE.ENGLISH

            var dataResult = ApiResult.success(User(name = userName, language = language, registeredAt = System.currentTimeMillis().toString()))
            dataResult
        },
        saveCallResult = {
            localDataSource.createUser(it)
        },
        parseNetworkResponse = {
            ApiResult.success(it)
        }
    )
}