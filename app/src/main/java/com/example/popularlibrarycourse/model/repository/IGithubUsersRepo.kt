package com.example.popularlibrarycourse.model.repository

import com.example.popularlibrarycourse.model.user.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}