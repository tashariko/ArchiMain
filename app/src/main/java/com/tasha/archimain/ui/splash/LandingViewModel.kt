package com.tasha.archimain.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(var repository: LandingRepository) : ViewModel() {

    private val _tempConfigLiveData = MutableLiveData<ApiResult<ConfigurationResponse>>()
    val configLiveData: LiveData<ApiResult<ConfigurationResponse>>
        get() = _tempConfigLiveData

    fun getConfig() {
        viewModelScope.launch {
            try {
                repository.getData().collect {
                    _tempConfigLiveData.postValue(it)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

    }

}