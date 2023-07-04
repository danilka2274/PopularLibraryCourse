package com.example.popularlibrarycourse.presenter.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.extensions.arguments
import com.example.popularlibrarycourse.extensions.setStartDrawableCircleImageFromUri
import com.example.popularlibrarycourse.extensions.showSnakeBar
import com.example.popularlibrarycourse.extensions.visible
import com.example.popularlibrarycourse.presenter.abs.AbsFragment
import com.example.popularlibrarycourse.presenter.repodetail.RepositoryScreen
import com.example.popularlibrarycourse.presenter.user.adapter.RepositoriesAdapter
import com.example.popularlibrarycourse.scheduler.Schedulers
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentUserBinding
import javax.inject.Inject


class UserFragment : AbsFragment(R.layout.fragment_user), IUserView,
    RepositoriesAdapter.Delegate {
    companion object {

        private const val ARG_USER = "arg_user"
        private const val ERROR_VALUE = "-1"

        fun newInstance(login: String): Fragment = UserFragment()
            .arguments(ARG_USER to login)
    }

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var repository: IRepository

    @Inject
    lateinit var router: Router

    private val vb: FragmentUserBinding by viewBinding()
    private val repositoriesAdapter = RepositoriesAdapter(delegate = this)

    private val login: String? by lazy { arguments?.getString(ARG_USER) }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            login = login ?: ERROR_VALUE,
            router = router,
            repository = repository,
            schedulers = schedulers
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setTitle()

        vb.repositoryText.visible { false }
        vb.rvRepositories.adapter = repositoriesAdapter
    }

    override fun showUser(user: GithubUser) {
        vb.login.text = user.login.uppercase()
        vb.login.setStartDrawableCircleImageFromUri(user.avatar)
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }

    override fun showRepositories(repositories: List<GitHubRepository>) {
        vb.repositoryText.visible { true }
        repositoriesAdapter.submitList(repositories)
    }

    override fun setTitle(title: String) {
        requireActivity().title = title
    }

    override fun onRepoPicked(repository: GitHubRepository) {
        login?.let {
            router.navigateTo(
                RepositoryScreen(
                    repository
                )
            )
        }
    }
}