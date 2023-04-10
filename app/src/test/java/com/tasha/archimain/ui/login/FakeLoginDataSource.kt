package com.tasha.archimain.ui.login

import com.tasha.archimain.data.source.local.entity.User

class FakeLoginDataSource: LoginDataSource {

    override suspend fun createUser(user: User) {

    }
}