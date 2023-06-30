package com.example.popularlibrarycourse.domain.repository

import io.reactivex.Maybe
import io.reactivex.Observable
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser

class MockRepositoryImpl : IRepository {

    private val users = listOf(
        GithubUser(userId = "0", login = "Иванов И.И.", "", ""),
        GithubUser(userId = "1", login = "Петров И.С.", "", ""),
        GithubUser(userId = "2", login = "Сидоров С.А.", "", ""),
        GithubUser(userId = "3", login = "Дудкин Б.Б.", "", ""),
        GithubUser(userId = "4", login = "Сорокин К.А.", "", "")
    )

    private val repositories = listOf(
        GitHubRepository(
            id = "1",
            login = "Иванов И.И.",
            "test",
            "descr1",
            "Ru",
            12,
            "master",
            "",
            1
        ),
        GitHubRepository(
            id = "2",
            login = "Иванов И.И.",
            "test2",
            "descr2",
            "Ru",
            11,
            "master",
            "",
            3
        ),
        GitHubRepository(
            id = "3",
            login = "Иванов И.И.",
            "test3",
            "descr3",
            "Ru",
            10,
            "master",
            "",
            6
        ),
        GitHubRepository(
            id = "4",
            login = "Петров И.С.",
            "test4",
            "descr4",
            "Ru",
            9,
            "master",
            "",
            7
        ),
        GitHubRepository(
            id = "5",
            login = "Сидоров С.А.",
            "test5",
            "descr5",
            "Ru",
            5,
            "master",
            "",
            2
        ),
        GitHubRepository(
            id = "6",
            login = "Дудкин Б.Б.",
            "test6",
            "descr6",
            "Ru",
            7,
            "master",
            "",
            6
        ),
        GitHubRepository(
            id = "7",
            login = "Сорокин К.А.",
            "test7",
            "descr7",
            "Ru",
            8,
            "master",
            "",
            12
        ),
    )

    override fun fetchUsers(): Observable<List<GithubUser>> = Observable.just(users)

    override fun fetchUserByLogin(login: String): Maybe<GithubUser> =
        users.firstOrNull { user -> user.login == login }
            ?.let { user -> Maybe.just(user) }
            ?: Maybe.error(Exception("Выбран несуществующий пользователь."))

    override fun fetchRepositoryInfo(
        login: String,
        repositoryName: String
    ): Maybe<GitHubRepository> =
        repositories.firstOrNull { repository -> (repository.login == login).and(repository.name == repositoryName) }
            ?.let { repository -> Maybe.just(repository) }
            ?: Maybe.error(Exception("Выбран несуществующий репозиторий."))

    override fun fetchUserRepositoriesByLogin(login: String): Observable<List<GitHubRepository>> =
        Observable.just(repositories.filter { repository -> repository.login == login })
}