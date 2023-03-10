package com.tasha.archimain.data.source.remote

import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import com.tasha.archimain.data.source.remote.response.ReMovie
import com.tasha.archimain.data.source.remote.response.TrendingItemResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MiscApiService {


    @GET("3/configuration")
    suspend fun configuration(): Response<ConfigurationResponse>

    @GET("3/trending/all/week")
    suspend fun getTrendingItems(
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): Response<TrendingItemResponse>
//
//    @GET("3/tv/{tv_id}")
//    suspend fun getTvDetail(@Query("tv_id") id: Long): Response<TV>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Long): Response<ReMovie>

}