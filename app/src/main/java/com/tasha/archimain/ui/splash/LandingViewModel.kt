package com.tasha.archimain.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(var repository: BaseLandingRepository) : ViewModel() {

    private val _tempConfigLiveData = MutableStateFlow(ApiResult.loading<ConfigurationResponse>())
    val configLiveData = _tempConfigLiveData

//    fun getConfig() {
//        viewModelScope.launch {
//            try {
//                repository.getData().collect {
//                    _tempConfigLiveData.emit(it)
//                }
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//    }

    private val _tempConfigLiveData1 = MutableLiveData<ApiResult<ConfigurationResponse>>()
    val configLiveData1: LiveData<ApiResult<ConfigurationResponse>>
        get() = _tempConfigLiveData1

    fun getConfig() {
        viewModelScope.launch {
            try {
                repository.getData().collect {
                    _tempConfigLiveData1.postValue(it)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

    }

}