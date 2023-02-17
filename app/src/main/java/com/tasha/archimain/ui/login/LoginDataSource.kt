package com.tasha.archimain.ui.login

import android.app.Application
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.data.source.local.dao.UserDao
import com.tasha.archimain.data.source.local.entity.User
import com.tasha.archimain.util.SharedPreferenceHelper
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(
    private val application: Application,
    private val userDao: UserDao
) {
    suspend fun createUser(user: User) {
        SharedPreferenceHelper.putInSharedPreference(
            application,
            AppConstants.SP_IS_LOGGED_IN,true)

        userDao.insert(user)
    }
}