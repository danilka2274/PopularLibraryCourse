package com.example.popularlibrarycourse.di


import com.example.popularlibrarycourse.MainActivity
import com.example.popularlibrarycourse.RepoDetailsFragment
import com.example.popularlibrarycourse.UserDetailsFragment
import com.example.popularlibrarycourse.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface GitHubUsersModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindUserDetailsFragment(): UserDetailsFragment

    @ContributesAndroidInjector
    fun bindRepoDetailsFragment(): RepoDetailsFragment


}