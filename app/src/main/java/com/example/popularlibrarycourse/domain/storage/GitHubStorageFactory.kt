package com.example.popularlibrarycourse.domain.storage

import android.content.Context
import androidx.room.Room

/****
Project PopularLibrary
Package softing.ubah4ukdev.popularlibrary.domain.storage.user

Created by Ivan Sheynmaer

2021.08.17
v1.0
 */
object GitHubStorageFactory {

    fun create(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            /**
             * Заменить .fallbackToDestructiveMigration() на .addMigrations(GitHub1to2Migration, ..),
             * где GitHub1to2Migration:
            object GitHub1to2Migration : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase)
            {
            database.execSQL("ALTER TABLE github_user ADD COLUMN migrate TEXT DEFAULT NULL")
            }
            }
             * Коммент оставил временно, в следующем реквесте удалю :)
             */
            .fallbackToDestructiveMigration()
            .build()

    fun createInMemory(context: Context): GitHubStorage =
        Room.inMemoryDatabaseBuilder(context, GitHubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()
}