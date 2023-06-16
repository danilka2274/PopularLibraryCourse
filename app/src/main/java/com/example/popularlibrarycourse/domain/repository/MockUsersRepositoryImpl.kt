package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.model.GithubUser

object MockUsersRepositoryImpl : IUsersRepository {
    private val users = listOf(
        GithubUser("Иванов И.И."),
        GithubUser("Петров И.С."),
        GithubUser("Сидоров С.А."),
        GithubUser("Дудкин Б.Б."),
        GithubUser("Сорокин К.А.")
    )

    override fun users(): List<GithubUser> {
        return users
    }
}