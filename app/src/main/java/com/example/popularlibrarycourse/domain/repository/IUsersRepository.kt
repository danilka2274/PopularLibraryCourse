package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.model.GithubUser
import io.reactivex.Maybe
import io.reactivex.Single

interface IUsersRepository {

    //Список пользователей
    fun users(): Single<List<GithubUser>>

    //Получить пользователя по userID
    fun userById(userId: Int): Maybe<GithubUser>
}