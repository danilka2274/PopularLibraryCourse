package com.example.popularlibrarycourse.domain.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import  com.example.popularlibrarycourse.BuildConfig



object GitHubApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header(
                    "Authorization",
                    Credentials.basic(BuildConfig.API_LOGIN, BuildConfig.API_TOKEN)
                )
                .build()
        )
}