package com.tasha.archimain.ui.login

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tasha.archimain.application.BaseActivity
import com.tasha.archimain.application.BaseViewModel
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.USER_LANGUAGE
import com.tasha.archimain.data.source.local.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) : BaseViewModel() {


    private val _tempUserLiveData = MutableLiveData<ApiResult<User>>()
    val createTaskLiveData: LiveData<ApiResult<User>>
        get() = _tempUserLiveData

    fun createUser(userName: String, language: String) {
        viewModelScope.launch {
            repository.createUser(userName, if(language == "hi") USER_LANGUAGE.HINDI else USER_LANGUAGE.ENGLISH).collect{
                _tempUserLiveData.value = it
            }
        }

    }

    fun getLanguage(context: BaseActivity, rgLanguage: RadioGroup) = (context.findViewById(rgLanguage.checkedRadioButtonId) as RadioButton).text.toString()

}