package com.example.popularlibrarycourse.domain.storage

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser

/****
Project PopularLibrary
Package softing.ubah4ukdev.popularlibrary.domain.storage.user

Created by Ivan Sheynmaer

2021.08.17
v1.0
 */
@Dao
interface GitHubDao {

    /**
     * Получить список пользователей из базы
     * @return List of GithubUser
     */
    @Query("SELECT * FROM github_user")
    fun fetchUsers(): Single<List<GithubUser>>

    /**
     * Получить пользователя по логину из базы
     * @param login Логин пользователя
     * @return GithubUser
     */
    @Query("SELECT * FROM github_user WHERE login LIKE :login LIMIT 1")
    fun fetchUserByLogin(login: String): Single<GithubUser>

    /**
     * Сохранить список пользователей в базу
     * @param users Список пользователей
     * @return List of GithubUser
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainUsers(users: List<GithubUser>): Completable

    /**
     * Обновить пользователя в базе
     * @param user Пользователь
     * @return GithubUser
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retainUsers(user: GithubUser): Completable

    /**
     * Сохранить список репозиториев в базу
     * @param repositories Список репозиториев
     * @return List of GitHubRepository
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainRepositories(repositories: List<GitHubRepository>): Completable

    /**
     * Обновить репозиторий в базе
     * @param repository Репозиторий
     * @return GitHubRepository
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainRepository(repository: GitHubRepository): Completable

    /**
     * Получить список репозиториев пользователя из базы
     * @param login Логин пользователя
     * @return List of GitHubRepository
     */
    @Query("SELECT * FROM github_user_repository WHERE login = :login")
    fun fetchUserRepositories(login: String): Single<List<GitHubRepository>>

    /**
     * Получить репозиторий пользователя по логину и названию из базы
     * @param login Логин пользователя
     * @param repositoryName Название репозитория
     * @return GitHubRepository
     */
    @Query("SELECT * FROM github_user_repository WHERE login = :login AND name = :repositoryName LIMIT 1")
    fun fetchRepositoryInfo(login: String, repositoryName: String): Single<GitHubRepository>
}