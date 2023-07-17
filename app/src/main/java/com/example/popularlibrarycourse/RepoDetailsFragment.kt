package com.example.popularlibrarycourse

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrarycourse.databinding.FragmentRepoDetailsBinding
import com.example.popularlibrarycourse.model.UserRepo
import com.example.popularlibrarycourse.model.user.GithubUser
import com.example.popularlibrarycourse.presenter.BackButtonListener
import com.example.popularlibrarycourse.presenter.RepoDetailsPresenter
import com.example.popularlibrarycourse.presenter.abs.AbsFragment
import com.example.popularlibrarycourse.view.RepoView
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class RepoDetailsFragment(
    private val user: GithubUser,
    private val repo: UserRepo
) :
    AbsFragment(R.layout.fragment_repo_details), RepoView, BackButtonListener {

    private val viewBinding by viewBinding(FragmentRepoDetailsBinding::bind)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: AndroidScreens

    private val presenter by moxyPresenter {
        RepoDetailsPresenter(
            repo,
            user,
            router,
            screens,
        )
    }

    companion object {
        fun newInstance(user: GithubUser, repo: UserRepo): Fragment
                = RepoDetailsFragment(user, repo)
    }

    @SuppressLint("SetTextI18n")
    override fun setRepoDetails(repo: UserRepo) {
        viewBinding.repoID.text = "Repo ID: " + repo.id.toString()
        viewBinding.repoName.text = "Repo name: " + repo.name
        viewBinding.repoLanguage.text = "Language: " + repo.language
        viewBinding.repoForks.text = "Forks: " + repo.forks.toString()
        viewBinding.repoCreatedAt.text = "Created at: " + repo.createdAt
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}