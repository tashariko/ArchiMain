package com.tasha.archimain.ui.login

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.USER_LANGUAGE
import com.tasha.archimain.data.source.local.entity.User
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        repository = Mockito.mock(LoginRepository::class.java)
        Mockito.`when`(repository.createUser("User", USER_LANGUAGE.ENGLISH)).thenReturn(
            flow {
                ApiResult.success(User(name = "User", language = USER_LANGUAGE.ENGLISH, registeredAt = System.currentTimeMillis().toString()))
            }
        )
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCreateTaskLiveData() {
    }

    @Test
    fun createUser() {
    }

    @Test
    fun getLanguage() {
    }
}