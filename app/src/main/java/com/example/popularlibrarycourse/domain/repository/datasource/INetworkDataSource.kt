package com.example.popularlibrarycourse.domain.repository.datasource

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single



interface INetworkDataSource {

    /**
     * Получить список пользователей
     * @return Список пользователей List of GithubUser
     */
    fun fetchUsers(): Single<List<GithubUser>>

    /**
     * Получить пользователя по логину
     * @param login Логин пользователя
     * @return GithubUser
     */
    fun fetchUserByLogin(login: String): Maybe<GithubUser>

    /**
     * Получить список репозиториев пользователя
     * @param login Логин пользователя
     * @return List of GitHubRepository
     */
    fun fetchUserRepositories(login: String): Single<List<GitHubRepository>>

    /**
     * Получить информацию о репозитории
     * @param login Логин пользователя
     * @param repositoryName Название репозитория
     * @return GitHubRepository
     */
    fun fetchRepositoryInfo(login: String, repositoryName: String): Single<GitHubRepository>
}