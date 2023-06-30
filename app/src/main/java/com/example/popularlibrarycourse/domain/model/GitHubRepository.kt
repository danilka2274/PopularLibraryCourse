package com.example.popularlibrarycourse.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "github_user_repository")
data class GitHubRepository(

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("login")
    val login: String = "",

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("forks_count")
    val forksCount: Int?,

    @SerializedName("defaulth_branch")
    val defaultBranch: String?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("size")
    val size: Int?,

    ) : Parcelable