package com.example.popularlibrarycourse.model.repository

import com.example.popularlibrarycourse.model.IDataSource
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.network.INetworkStatus
import com.example.popularlibrarycourse.model.user.IUsersCache
import com.example.popularlibrarycourse.model.user.RoomGithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database,
    private val usersCache: IUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap({ users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(
                                user.id ?: "",
                                user.login ?: "",
                                user.avatarUrl ?: "",
                                user.repoListUrl ?: ""
                            )
                        }
                        usersCache.setCachedData(roomUsers)
                        users
                    }
                }, { _ ->
                    usersCache.getCachedData()
                })
        } else {
            usersCache.getCachedData()
        }
    }.subscribeOn(Schedulers.io())
}