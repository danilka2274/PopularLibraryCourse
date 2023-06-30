package com.example.popularlibrarycourse.domain.repository.datasource

import com.example.popularlibrarycourse.domain.api.GitHubApiFactory



object NetworkDataSourceFactory {

    fun create(): INetworkDataSource = NetworkDataSourceImpl(GitHubApiFactory.create())
}