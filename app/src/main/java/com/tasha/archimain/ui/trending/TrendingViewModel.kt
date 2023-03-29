package com.tasha.archimain.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.TrendingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var repository: TrendingRepository

    private val _tempTrendingListLiveData = MutableLiveData<ApiResult<List<TrendingItem>>>()
    val trendingListLiveData: LiveData<ApiResult<List<TrendingItem>>>
        get() = _tempTrendingListLiveData

    fun fetchTrendingList() {
        viewModelScope.launch {
            try {
                repository.getData(page = 1).collect {
                    _tempTrendingListLiveData.value = it
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}