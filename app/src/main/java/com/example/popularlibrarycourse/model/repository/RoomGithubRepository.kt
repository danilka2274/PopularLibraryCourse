package com.example.popularlibrarycourse.model.repository

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.popularlibrarycourse.model.user.RoomGithubUser


@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey var id: Long,
    var name: String,
    var language: String,
    var forksCount: Int,
    var userId: String,
    var createdAt: String
)