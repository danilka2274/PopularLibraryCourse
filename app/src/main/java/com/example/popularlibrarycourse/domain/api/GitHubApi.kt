package com.example.popularlibrarycourse.domain.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser


interface GitHubApi {

    /**
     * Получить список пользователей
     * @param since
     * @return List of GithubUser
     */
    @GET("/users")
    fun fetchUsers(@Query("since") since: Int? = null): Single<List<GithubUser>>

    /**
     * Получить пользователя по логину
     * @param login Логин пользователя
     * @return GithubUser
     */
    @GET("/users/{username}")
    fun fetchUserByLogin(@Path("username") login: String): Single<GithubUser>

    /**
     * Получить список репозиториев пользователя
     * @param login Логин пользователя
     * @return List of GithubUser
     */
    @GET("users/{login}/repos")
    fun fetchUserRepositories(@Path("login") login: String): Single<List<GitHubRepository>>

    /**
     * Получить детальную информацию о репозитории
     * @param login Логин пользователя
     * @param repositoryName Название репозитория
     * @return GitHubRepository
     */
    @GET("repos/{login}/{name}")
    fun fetchRepositoryInfo(
        @Path("login") login: String,
        @Path("name") repositoryName: String,
    ): Single<GitHubRepository>
}