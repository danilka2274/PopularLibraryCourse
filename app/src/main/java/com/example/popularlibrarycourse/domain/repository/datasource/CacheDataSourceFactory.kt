package com.example.popularlibrarycourse.domain.repository.datasource

import com.example.popularlibrarycourse.App.ContextHolder
import com.example.popularlibrarycourse.domain.storage.GitHubStorageFactory


object CacheDataSourceFactory {

    fun create(): ICacheDataSource =
        CacheDataSourceImpl(
            GitHubStorageFactory.create(ContextHolder.context)
        )
}