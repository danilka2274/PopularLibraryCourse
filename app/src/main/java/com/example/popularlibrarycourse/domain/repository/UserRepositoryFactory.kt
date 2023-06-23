package com.example.popularlibrarycourse.domain.repository

object UserRepositoryFactory {

    fun create(): IUsersRepository = MockUsersRepositoryImpl()
}