package com.example.popularlibrarycourse.model.repository

import com.example.popularlibrarycourse.model.UserRepo
import com.example.popularlibrarycourse.model.user.GithubUser
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun setCachedData(roomRepos : List<RoomGithubRepository>)
    fun getCachedData(user: GithubUser): Single<List<UserRepo>>
}