package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.model.GithubUser

interface IUsersRepository {

    //Список пользователей
    fun users(): List<GithubUser>
}