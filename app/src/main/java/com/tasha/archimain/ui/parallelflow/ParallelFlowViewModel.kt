package com.tasha.archimain.ui.parallelflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.ui.mainflow.movie.MovieRepository
import com.tasha.archimain.ui.mainflow.tv.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParallelFlowViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var tvRepository: TvRepository

    @Inject
    lateinit var movieRepository: MovieRepository

    private val _tempTVTrendingLiveData = MutableLiveData<ApiResult<List<TrendingItem>>>()
    val tvTrendingLiveData: LiveData<ApiResult<List<TrendingItem>>>
        get() = _tempTVTrendingLiveData

    private val _tempMovieTrendingLiveData = MutableLiveData<ApiResult<List<TrendingItem>>>()
    val movieTrendingLiveData: LiveData<ApiResult<List<TrendingItem>>>
        get() = _tempMovieTrendingLiveData

    fun fetchList() {
        viewModelScope.launch {
            launch {
                try {
                    movieRepository.getMovieData(1).collect {
                        delay(3000)
                        _tempMovieTrendingLiveData.value = it
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            launch {
                try {
                    tvRepository.getTVData(1).collect {
                        delay(5000)
                        _tempTVTrendingLiveData.value = it
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }
}