package com.example.popularlibrarycourse.presenter.user.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.R


class RepositoriesAdapter(private val delegate: Delegate?) :
    ListAdapter<GitHubRepository, RepositoryViewHolder>(RepoDiff) {
    interface Delegate {

        /**
         * Событие наступает при выборе
         * репозитория из списка.
         * @param repository Репозиторий
         */
        fun onRepoPicked(repository: GitHubRepository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
        RepositoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.repository, parent, false)
        )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)
}