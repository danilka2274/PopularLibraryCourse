package com.example.popularlibrarycourse.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "github_user")
data class GithubUser(

    @PrimaryKey
    @SerializedName("id")
    val userId: String,

    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("repos_url")
    val repos: String

) : Parcelable