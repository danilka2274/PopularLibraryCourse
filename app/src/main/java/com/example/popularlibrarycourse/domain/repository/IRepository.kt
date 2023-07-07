package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Observable


interface IRepository {

    /**
     * Получить список пользователей
     * @return List of GithubUser
     */
    fun fetchUsers(): Observable<List<GithubUser>>

    /**
     * Получить пользователя по логину
     * @param login Логин пользователя
     * @return GithubUser
     */
    fun fetchUserByLogin(login: String): Maybe<GithubUser>

    /**
     * Получить информацию о репозитории
     * @param login Логин пользователя
     * @return GitHubRepository
     */
    fun fetchRepositoryInfo(login: String, repositoryName: String): Maybe<GitHubRepository>

    /**
     * Получить список репозиториев пользователя
     * @param login Логин пользователя
     * @return List of GithubUser
     */
    fun fetchUserRepositoriesByLogin(login: String): Observable<List<GitHubRepository>>
}