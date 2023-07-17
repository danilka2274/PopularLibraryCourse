package com.example.popularlibrarycourse.model.repository

import com.example.popularlibrarycourse.model.UserRepo
import com.example.popularlibrarycourse.model.user.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserReposList {
    fun getUserRepoList(user: GithubUser): Single<List<UserRepo>>
}