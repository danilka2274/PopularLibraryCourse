package com.example.popularlibrarycourse.model.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibrarycourse.model.repository.RepositoryDao
import com.example.popularlibrarycourse.model.repository.RoomGithubRepository
import com.example.popularlibrarycourse.model.user.RoomGithubUser
import com.example.popularlibrarycourse.model.user.UserDao



@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}