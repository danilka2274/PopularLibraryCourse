package com.example.popularlibrarycourse.domain.repository.datasource

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Single



interface ICacheDataSource: INetworkDataSource {

    /**
     * Сохранить список пользователей
     * @param users Список пользователей
     * @return List of GithubUser
     */
    fun retainUsers(users: List<GithubUser>): Single<List<GithubUser>>

    /**
     * Обновить пользователя
     * @param user Пользователей
     * @return GithubUser
     */
    fun retainUser(user: GithubUser): Single<GithubUser>

    /**
     * Сохранить список репозиториев
     * @param repositories Список репозиториев
     * @param login Логин пользователя
     * @return List of GitHubRepository
     */
    fun retainRepositories(repositories: List<GitHubRepository>, login:String): Single<List<GitHubRepository>>

    /**
     * Обновить репозиторий
     * @param reposizoty Репозиторий
     * @param login Логин пользователя
     * @return GitHubRepository
     */
    fun retainRepository(reposizoty: GitHubRepository, login:String, repositoryName:String): Single<GitHubRepository>
}