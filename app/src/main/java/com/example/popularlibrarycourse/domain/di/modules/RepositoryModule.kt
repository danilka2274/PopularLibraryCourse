package com.example.popularlibrarycourse.domain.di.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.example.popularlibrarycourse.MainActivity
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.domain.repository.RepositoryImpl
import com.example.popularlibrarycourse.domain.repository.datasource.CacheDataSourceImpl
import com.example.popularlibrarycourse.domain.repository.datasource.ICacheDataSource
import com.example.popularlibrarycourse.domain.repository.datasource.INetworkDataSource
import com.example.popularlibrarycourse.domain.repository.datasource.NetworkDataSourceImpl
import com.example.popularlibrarycourse.presenter.convert.ConvertFragment
import com.example.popularlibrarycourse.presenter.repodetail.RepositoryFragment
import com.example.popularlibrarycourse.presenter.user.UserFragment
import com.example.popularlibrarycourse.presenter.users.UsersFragment
import javax.inject.Singleton


@Module
interface RepositoryModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindConvertFragment(): ConvertFragment

    @ContributesAndroidInjector
    fun bindRepositoryFragment(): RepositoryFragment

    @Singleton
    @Binds
    fun bindGitHubUserRepository(repository: RepositoryImpl): IRepository

    @Singleton
    @Binds
    fun bindUserDataSource(dataSource: CacheDataSourceImpl): ICacheDataSource

    @Singleton
    @Binds
    fun bindCacheUserDataSource(dataSource: NetworkDataSourceImpl): INetworkDataSource
}