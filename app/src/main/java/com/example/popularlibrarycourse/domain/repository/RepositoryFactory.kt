package com.example.popularlibrarycourse.domain.repository

import com.example.popularlibrarycourse.domain.repository.datasource.CacheDataSourceFactory
import com.example.popularlibrarycourse.domain.repository.datasource.NetworkDataSourceFactory



object RepositoryFactory {

    private val repository: IRepository by lazy {
        RepositoryImpl(
            NetworkDataSourceFactory.create(),
            CacheDataSourceFactory.create()
        )
    }

    fun create(): IRepository = repository
}