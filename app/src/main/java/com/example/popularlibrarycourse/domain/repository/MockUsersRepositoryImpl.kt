package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Фиксированный репозиторий для отладки
 */
class MockUsersRepositoryImpl : IUsersRepository {
    private val users = listOf(
        GithubUser(userId = "0", login = "Иванов И.И.", ""),
        GithubUser(userId = "1", login = "Петров И.С.", ""),
        GithubUser(userId = "2", login = "Сидоров С.А.", ""),
        GithubUser(userId = "3", login = "Дудкин Б.Б.", ""),
        GithubUser(userId = "4", login = "Сорокин К.А.", "")
    )

    override fun users(): Single<List<GithubUser>> = Single.just(users)

    override fun userById(login: String): Maybe<GithubUser> =
        users.firstOrNull { user -> user.userId.equals(login) }
            ?.let { user -> Maybe.just(user) }
            ?: Maybe.error(Exception("Выбран несуществующий пользователь."))
}