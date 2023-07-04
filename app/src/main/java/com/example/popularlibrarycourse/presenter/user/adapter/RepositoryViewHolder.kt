package com.example.popularlibrarycourse.presenter.user.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.extensions.click
import com.example.popularlibrarycourse.databinding.RepositoryBinding


class RepositoryViewHolder(view: View) : ViewHolder(view) {
    private val viewBinding: RepositoryBinding by viewBinding()

    fun bind(gitHubRepository: GitHubRepository, delegate: RepositoriesAdapter.Delegate?) {
        with(viewBinding) {
            repositoryName.text = gitHubRepository.name
            root.click { delegate?.onRepoPicked(gitHubRepository) }
        }
    }
}