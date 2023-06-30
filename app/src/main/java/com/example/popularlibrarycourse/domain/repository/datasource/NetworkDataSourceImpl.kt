package com.example.popularlibrarycourse.domain.repository.datasource

import com.example.popularlibrarycourse.domain.api.GitHubApi
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single


class NetworkDataSourceImpl(private val gitHubApi: GitHubApi) : INetworkDataSource {

    override fun fetchUsers(): Single<List<GithubUser>> =
        gitHubApi.fetchUsers()

    override fun fetchUserByLogin(login: String): Maybe<GithubUser> =
        gitHubApi.fetchUserByLogin(login)
            .toMaybe()

    override fun fetchUserRepositories(login: String): Single<List<GitHubRepository>> =
        gitHubApi.fetchUserRepositories(login)

    override fun fetchRepositoryInfo(login: String, repositoryName: String): Single<GitHubRepository> =
        gitHubApi.fetchRepositoryInfo(login, repositoryName)
}