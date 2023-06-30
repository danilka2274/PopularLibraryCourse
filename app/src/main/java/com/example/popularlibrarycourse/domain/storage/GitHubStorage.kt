package com.example.popularlibrarycourse.domain.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser

/****
Project PopularLibrary
Package softing.ubah4ukdev.popularlibrary.domain.storage

Created by Ivan Sheynmaer

2021.08.17
v1.0
 */
@Database(
    exportSchema = false,
    entities = [GithubUser::class, GitHubRepository::class],
    version = 9
)
abstract class GitHubStorage : RoomDatabase() {

    abstract fun gitHubUserDao(): GitHubDao
}