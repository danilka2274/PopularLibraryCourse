package com.example.popularlibrarycourse.domain.storage

import androidx.room.*
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface GitHubDao {

    /**
     * Получить список пользователей из базы
     * @return List of GithubUser
     */
    @Query("SELECT * FROM github_user")
    fun users(): Single<List<GithubUser>>

    /**
     * Получить пользователя по логину из базы
     * @param login Логин пользователя
     * @return GithubUser
     */
    @Query("SELECT * FROM github_user WHERE login LIKE :login LIMIT 1")
    fun userByLogin(login: String): Single<GithubUser>

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
     * @param repos Список репозиториев
     * @return List of GitHubRepository
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainRepositories(repos: List<GitHubRepository>): Completable

    /**
     * Обновить репозиторий в базе
     * @param repo Репозиторий
     * @return GitHubRepository
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainRepository(repo: GitHubRepository): Completable

    /**
     * Получить список репозиториев пользователя из базы
     * @param login Логин пользователя
     * @return List of GitHubRepository
     */
    @Query("SELECT * FROM github_user_repository WHERE login = :login")
    fun repoList(login: String): Single<List<GitHubRepository>>

    /**
     * Получить репозиторий пользователя по логину и названию из базы
     * @param login Логин пользователя
     * @param name Название репозитория
     * @return GitHubRepository
     */
    @Query("SELECT * FROM github_user_repository WHERE login = :login AND name = :name LIMIT 1")
    fun repoInfo(login: String, name: String): Single<GitHubRepository>
}