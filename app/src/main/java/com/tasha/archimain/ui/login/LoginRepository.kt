package com.tasha.archimain.ui.login

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.USER_LANGUAGE
import com.tasha.archimain.data.source.local.entity.User
import com.tasha.archimain.network.BaseRepository
import javax.inject.Inject


class LoginRepository  @Inject constructor(
    private val localDataSource: LoginLocalDataSource
){
    fun createUser(userName: String, language: USER_LANGUAGE)  = object : BaseRepository<User,User>() {
        override fun shouldfetchDataFromDbBeforeNetwork() = false
            }.repoWork(
                databaseQuery = {
                    null
                },
                networkCall = {
                    ApiResult.success(User(name = userName, language = language, registeredAt = System.currentTimeMillis().toString()))
                },
                saveCallResult = {
                    localDataSource.createUser(it)
                },
                parseNetworkResponse = {
                    ApiResult.success(it)
                }
            )
}