package com.example.popularlibrarycourse.presenter.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.domain.repository.UserRepositoryFactory
import com.example.popularlibrarycourse.extensions.arguments
import com.example.popularlibrarycourse.extensions.setStartDrawableCircleImageFromUri
import com.example.popularlibrarycourse.extensions.visible
import com.example.popularlibrarycourse.presenter.repodetail.RepositoryScreen
import com.example.popularlibrarycourse.presenter.user.adapter.RepositoriesAdapter
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.example.popularlibrarycourse.scheduler.SchedulerFactory
import com.example.popularlibrarycourse.ui.extensions.showSnakeBar
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentUserBinding


class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), IUserView,  RepositoriesAdapter.Delegate{
    companion object {

        private const val ARG_USER = "arg_user"
        private const val ERROR_VALUE = "-1"

        fun newInstance(login: String): Fragment = UserFragment()
            .arguments(ARG_USER to login)
    }

    private val vb: FragmentUserBinding by viewBinding()
    private val repoAdapter = RepositoriesAdapter(delegate = this)

    private val login: String? by lazy { arguments?.getString(ARG_USER) }
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            login = login ?: ERROR_VALUE,
            router = router,
            UserRepositoryFactory.create(),
            SchedulerFactory.create()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.repoTitle.visible { false }
        vb.rvRepos.adapter = repoAdapter
    }

    override fun showUser(user: GithubUser) {
        vb.tvLogin.text = user.login
        vb.tvLogin.setStartDrawableCircleImageFromUri(user.avatar)
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }

    override fun showRepo(repos: List<GitHubRepository>) {
        vb.repoTitle.visible { true }
        repoAdapter.submitList(repos)
    }

    override fun onRepoPicked(repository: GitHubRepository) {
        router.replaceScreen(RepositoryScreen(repository).create())
    }
}}