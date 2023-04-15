package com.tasha.archimain.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tasha.archimain.BuildConfig
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.data.source.local.AppDatabase
import com.tasha.archimain.network.RequestInterceptor
import com.tasha.archimain.ui.splash.BaseLandingRepository
import com.tasha.archimain.ui.splash.LandingLocalDataSource
import com.tasha.archimain.ui.splash.LandingRemoteDataSource
import com.tasha.archimain.ui.splash.LandingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesBaseUrl(): String = if (BuildConfig.DEBUG) "https://api.themoviedb.org/" else "https://api.themoviedb.org/"

    @Provides
    @Singleton
    fun providesRequestInterceptor(application: Application): RequestInterceptor = RequestInterceptor(application.applicationContext, BuildConfig.API_DEVELOPER_TOKEN)

    @Provides
    @Singleton
    fun providesOkHttpClient(
        application: Application,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient {

        return if (BuildConfig.DEBUG) {
            val chuckerCollector = ChuckerCollector(
                context = application,
                // Toggles visibility of the notification
                showNotification = true,
                // Allows to customize the retention period of collected data
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )
            val chuckerInterceptor = ChuckerInterceptor.Builder(application)
                .collector(chuckerCollector)
                .alwaysReadResponseBody(true)
                .build()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        } else {
            OkHttpClient.Builder()
                .addNetworkInterceptor(requestInterceptor)
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object LandingRepositoryModule {

    @Singleton
    @Provides
    fun provideLandingRepository(
        localDataSource: LandingLocalDataSource,
        remoteDataSource: LandingRemoteDataSource
    ): BaseLandingRepository {
        return LandingRepository(
            remoteDataSource, localDataSource
        )
    }
}

