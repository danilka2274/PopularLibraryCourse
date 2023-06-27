package com.example.popularlibrarycourse.presenter.user.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.popularlibrarycourse.domain.model.GitHubRepository

object RepoDiff : DiffUtil.ItemCallback<GitHubRepository>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean {
        return oldItem.equals(newItem)
    }

    override fun getChangePayload(oldItem: GitHubRepository, newItem: GitHubRepository) = payload

}