package com.example.popularlibrarycourse.presenter.repodetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.extensions.arguments
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentRepositoryBinding


class RepositoryFragment : MvpAppCompatFragment(R.layout.fragment_repository), IRepositoryView {

    companion object {
        private const val ARG_REPO = "arg_repo"

        fun newInstance(repo: GitHubRepository): Fragment = RepositoryFragment()
            .arguments(ARG_REPO to repo)
    }

    private val vb: FragmentRepositoryBinding by viewBinding()
    private val repo: GitHubRepository? by lazy { arguments?.getParcelable(ARG_REPO) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setTitle()
    }

    private val presenter: RepositoryPresenter by moxyPresenter {
        RepositoryPresenter(
            repo = repo ?: throw Throwable(IllegalArgumentException())
        )
    }

    override fun showDetail(repo: GitHubRepository) {

        with(vb) {
            repoId.text = "id: ${repo.id}"
            repoName.text = "Название: ${repo.name}"
            repoDescription.text = "Описание: ${repo.description}"
            repoLanguage.text = "Язык: ${repo.language}"
            repoForksCount.text = "Форков ${repo.forksCount} шт."
            repoBranch.text = "Ветка по умолчанию: ${repo.defaultBranch}"
            repoDateCreated.text = "Дата создания: ${repo.createdAt}"
            repoSize.text = "Размер репозитория: ${repo.size}"
        }
    }

    override fun setTitle(title: String) {
        requireActivity().title = title
    }
}