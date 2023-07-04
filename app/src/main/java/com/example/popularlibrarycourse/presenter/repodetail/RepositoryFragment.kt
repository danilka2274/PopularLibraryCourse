package com.example.popularlibrarycourse.presenter.repodetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.extensions.arguments
import com.example.popularlibrarycourse.extensions.showSnakeBar
import com.example.popularlibrarycourse.presenter.abs.AbsFragment
import com.example.popularlibrarycourse.scheduler.Schedulers
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentRepositoryBinding
import javax.inject.Inject


class RepositoryFragment : AbsFragment(R.layout.fragment_repository), IRepositoryView {

    companion object {
        private const val ARG_REPO = "arg_repo"

        fun newInstance(repo: GitHubRepository): Fragment = RepositoryFragment()
            .arguments(ARG_REPO to repo)
    }

    @Inject
    lateinit var repository: IRepository

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var router: Router

    private val vb: FragmentRepositoryBinding by viewBinding()
    private val gitHubRepository: GitHubRepository? by lazy { arguments?.getParcelable(ARG_REPO) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setTitle()
    }

    private val presenter: RepositoryPresenter by moxyPresenter {
        RepositoryPresenter(
            gitHubRepository = gitHubRepository ?: throw Throwable(IllegalArgumentException()),
            schedulers = schedulers,
            repository = repository
        )
    }

    override fun showDetail(repo: GitHubRepository) {

        with(vb) {
            repositoryId.text = repo.id
            repositoryName.text = repo.name
            repositoryDescription.text = "${repo.description}"
            repositoryLanguage.text = "${repo.language}"
            "${repo.forksCount} шт.".also { repositoryForksCount.text = it }
            repositoryBranch.text = "${repo.defaultBranch}"
            repositoryDateCreated.text = "${repo.createdAt}"
            repositorySize.text = "${repo.size}"
        }
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(text = message)
    }

    override fun setTitle(title: String) {
        requireActivity().title = title
    }
}