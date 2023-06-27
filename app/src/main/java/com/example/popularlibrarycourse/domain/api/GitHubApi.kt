package com.example.popularlibrarycourse.domain.api

import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    /**
     * Получить список пользователей
     * @param since
     * @return Список пользователей List of GithubUser
     */

    @GET("/users")
    fun users(@Query("since") since: Int? = null): Single<List<GithubUser>>

    /**
     * Получить пользователя по логину
     * @param login Логин пользователя
     * @return GithubUser
     */
    @GET("/users/{username}")
    fun userById(@Path("username") login: String): Single<GithubUser>




}