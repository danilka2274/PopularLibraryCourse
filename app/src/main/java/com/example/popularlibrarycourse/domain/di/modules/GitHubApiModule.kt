package com.example.popularlibrarycourse.domain.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.popularlibrarycourse.domain.api.GitHubApi
import com.example.popularlibrarycourse.domain.api.GitHubApiInterceptor
import com.example.popularlibrarycourse.BuildConfig
import javax.inject.Singleton


@Module
class GitHubApiModule {

    private val gson: Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideGitHubApi(): GitHubApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(GitHubApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubApi::class.java)
}