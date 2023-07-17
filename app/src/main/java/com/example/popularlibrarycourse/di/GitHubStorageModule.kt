package com.example.popularlibrarycourse.di

import android.content.Context
import androidx.room.Room
import com.example.popularlibrarycourse.model.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object GitHubStorageModule {

    @Singleton
    @Provides
    fun provideGitHubDatabaseStorage(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "github.db")
            .build()
}