package com.example.popularlibrarycourse.presenter

import com.example.popularlibrarycourse.model.user.GithubUser
import com.example.popularlibrarycourse.model.UserRepo
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.network.AndroidNetworkStatus
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(networkStatus: AndroidNetworkStatus): Screen
    fun userDetails(user: GithubUser, db: Database): Screen
    fun repoDetails(user: GithubUser, repo: UserRepo): Screen
}