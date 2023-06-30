package com.example.popularlibrarycourse.domain.repository.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.domain.storage.GitHubStorage


class CacheDataSourceImpl(private val gitHubStorage: GitHubStorage) :
    ICacheDataSource {

    override fun retainUsers(users: List<GithubUser>): Single<List<GithubUser>> =
        gitHubStorage
            .gitHubUserDao()
            .retainUsers(users)
            .andThen(fetchUsers())

    override fun retainUser(user: GithubUser): Single<GithubUser> =
        gitHubStorage
            .gitHubUserDao()
            .retainUsers(user)
            .andThen(fetchUserByLogin(user.login))
            .toSingle()

    override fun retainRepositories(
        repositories: List<GitHubRepository>,
        login: String
    ): Single<List<GitHubRepository>> =
        gitHubStorage
            .gitHubUserDao()
            .retainRepositories(repositories)
            .andThen(fetchUserRepositories(login))

    override fun retainRepository(
        repository: GitHubRepository,
        login: String,
        repositoryName: String
    ): Single<GitHubRepository> =
        gitHubStorage
            .gitHubUserDao()
            .retainRepository(repository)
            .andThen(fetchRepositoryInfo(login, repositoryName))

    override fun fetchUsers(): Single<List<GithubUser>> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUsers()

    override fun fetchUserByLogin(login: String): Maybe<GithubUser> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUserByLogin(login)
            .toMaybe()


    override fun fetchUserRepositories(login: String): Single<List<GitHubRepository>> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUserRepositories(login)

    override fun fetchRepositoryInfo(login: String, repositoryName: String): Single<GitHubRepository> =
        gitHubStorage
            .gitHubUserDao()
            .fetchRepositoryInfo(login, repositoryName)
}