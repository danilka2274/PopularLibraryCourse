package com.example.popularlibrarycourse.presenter.repodetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.repository.RepositoryFactory
import com.example.popularlibrarycourse.extensions.arguments
import com.example.popularlibrarycourse.extensions.showSnakeBar
import com.example.popularlibrarycourse.scheduler.SchedulerFactory
import com.example.popularlibrarycourse.ui.IBackButtonListener
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentRepositoryBinding


class RepositoryFragment : MvpAppCompatFragment(R.layout.fragment_repository), IRepositoryView,
    IBackButtonListener {

    companion object {
        private const val ARG_REPO = "arg_repo"

        fun newInstance(repo: GitHubRepository): Fragment = RepositoryFragment()
            .arguments(ARG_REPO to repo)
    }

    private val vb: FragmentRepositoryBinding by viewBinding()
    private val gitHubRepository: GitHubRepository? by lazy { arguments?.getParcelable(ARG_REPO) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setTitle()
    }

    private val presenter: RepositoryPresenter by moxyPresenter {
        RepositoryPresenter(
            userRepository = gitHubRepository ?: throw Throwable(IllegalArgumentException()),
            schedulers = SchedulerFactory.create(),
            repository = RepositoryFactory.create()
        )
    }

    override fun showDetail(repo: GitHubRepository) {

        with(vb) {
            repoId.text = repo.id
            repoName.text = repo.name
            repoDescription.text = "${repo.description}"
            repoLanguage.text = "${repo.language}"
            "${repo.forksCount} шт.".also { repoForksCount.text = it }
            repoBranch.text = "${repo.defaultBranch}"
            repoDateCreated.text = "${repo.createdAt}"
            repoSize.text = "${repo.size}"
        }
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(text = message)
    }

    override fun setTitle(title: String) {
        requireActivity().title = title.uppercase()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}