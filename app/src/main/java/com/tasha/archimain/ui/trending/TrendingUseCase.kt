package com.tasha.archimain.ui.trending

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.ErrorType
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.ui.movie.MovieRepository
import com.tasha.archimain.ui.tv.TvRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TrendingUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
){

    fun getData(page: Int): Flow<ApiResult<List<TrendingItem>>> {
        val movieStream = movieRepository.getMovieData(page)
        val tvStream = tvRepository.getTVData(page)

        return combine(movieStream, tvStream) { movie, tv ->
            val list = ArrayList<TrendingItem>()
            movie.data?.let{
                list.addAll(it)
            }
            tv.data?.let{
                list.addAll(it)
            }
            if(movie.status == ApiResult.Status.LOADING || tv.status == ApiResult.Status.LOADING){
                ApiResult.loading(list)
            }else if(movie.status == ApiResult.Status.ERROR || tv.status == ApiResult.Status.ERROR){
                val error = movie.errorType?: tv.errorType
                ApiResult.error(errorType = error,list)
            }else if(movie.status  == ApiResult.Status.SUCCESS && tv.status == ApiResult.Status.SUCCESS){
                ApiResult.success(list)
            }else{
                ApiResult.error(errorType = ErrorType(ErrorType.Type.Generic),list)
            }
        }
    }
}