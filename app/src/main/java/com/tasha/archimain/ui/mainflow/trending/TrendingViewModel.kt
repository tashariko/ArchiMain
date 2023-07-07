package com.tasha.archimain.ui.mainflow.trending

import androidx.lifecycle.*
import kotlinx.coroutines.*
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.TrendingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var trendingUseCase: TrendingUseCase

    private val _tempTrendingLiveData = MutableLiveData<ApiResult<List<TrendingItem>>>()
    val trendingLiveData: LiveData<ApiResult<List<TrendingItem>>>
        get() = _tempTrendingLiveData

    fun fetchTrendingList() {
        viewModelScope.launch {
            try {
                trendingUseCase.getData(DEFAULT_TRENIND_PAGE_SIZE).collect{
                    _tempTrendingLiveData.value = it
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}