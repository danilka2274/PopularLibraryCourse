package com.example.popularlibrarycourse.domain.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.example.popularlibrarycourse.domain.di.InMemory
import com.example.popularlibrarycourse.domain.di.Persisted
import com.example.popularlibrarycourse.domain.storage.GitHubStorage


@Module
class GitHubStorageModule {

    @Persisted
    @Provides
    fun provideGitHubDatabaseStorage(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            .build()

    @InMemory
    @Provides
    fun provideGitHubInMemoryDatabaseStorage(context: Context): GitHubStorage =
        Room.inMemoryDatabaseBuilder(context, GitHubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()
}