package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.api.GitHubApiFactory

object UserRepositoryFactory {

    //fun create(): IUsersRepository = MockUsersRepositoryImpl()
    fun create(): IUsersRepository = GitHubUsersRepositoryImpl(GitHubApiFactory.create())
}