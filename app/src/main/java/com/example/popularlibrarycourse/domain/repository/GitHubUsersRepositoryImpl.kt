package com.example.popularlibrarycourse.domain.repository

import io.reactivex.Maybe
import io.reactivex.Single
import com.example.popularlibrarycourse.domain.api.GitHubApi
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser


class GitHubUsersRepositoryImpl(
    private val apiRepository: GitHubApi
) : IUsersRepository {
    override fun users(): Single<List<GithubUser>> =
        apiRepository.fetchUsers()

    override fun userById(login: String): Maybe<GithubUser> =
        apiRepository.fetchUserByLogin(login)
            .toMaybe()

    override fun repoList(login: String): Single<List<GitHubRepository>> =
        apiRepository.fetchUserRepositoriesByLogin(login)
}