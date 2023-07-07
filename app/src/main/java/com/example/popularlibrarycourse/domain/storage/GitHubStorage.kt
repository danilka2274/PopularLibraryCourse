package com.example.popularlibrarycourse.domain.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser



@Database(
    exportSchema = false,
    entities = [GithubUser::class, GitHubRepository::class],
    version = 9
)
abstract class GitHubStorage : RoomDatabase() {

    abstract fun gitHubUserDao(): GitHubDao
}