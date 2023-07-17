package com.example.popularlibrarycourse

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrarycourse.adapters.RepoRVAdapter
import com.example.popularlibrarycourse.databinding.FragmentDetailsBinding
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.network.AndroidNetworkStatus
import com.example.popularlibrarycourse.model.repository.RetrofitGithubUserReposList
import com.example.popularlibrarycourse.model.repository.RoomRepositoriesCache
import com.example.popularlibrarycourse.model.user.GithubUser
import com.example.popularlibrarycourse.presenter.BackButtonListener
import com.example.popularlibrarycourse.presenter.GlideImageLoader
import com.example.popularlibrarycourse.presenter.UserDetailsPresenter
import com.example.popularlibrarycourse.presenter.abs.AbsFragment
import com.example.popularlibrarycourse.utils.ApiHolder
import com.example.popularlibrarycourse.view.UserDetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class UserDetailsFragment(
    private val user: GithubUser,
    private val db: Database
) :
    AbsFragment(R.layout.fragment_details), BackButtonListener, UserDetailsView {

    companion object {
        private const val ARG_USER = "user"

        fun newInstance(user: GithubUser, db: Database, text : String): Fragment {
            val fragment = UserDetailsFragment(user, db)
            fragment.arguments?.putString("login", text)
            return fragment
        }

//        = UserDetailsFragment(user, db).arguments("test" to text)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var networkStatus : AndroidNetworkStatus

    @Inject
    lateinit var mainThread: Scheduler

    @Inject
    lateinit var screens: AndroidScreens

    private val userReposList = RoomRepositoriesCache.getInstance()

    private val presenter by moxyPresenter {
        UserDetailsPresenter(
            networkStatus,
            RetrofitGithubUserReposList(ApiHolder.api, networkStatus, db, userReposList),
            router,
            user,
            mainThread,
            screens
        )
    }

    private val viewBinding by viewBinding(FragmentDetailsBinding::bind)

    private var adapter: RepoRVAdapter? = null

    override fun backPressed() = presenter.backPressed()

    override fun setUserPage(user: GithubUser) {
        viewBinding.detailsName.text = user.login
        user.avatarUrl?.let { GlideImageLoader().loadInto(it, viewBinding.detailsUserImage) }

        viewBinding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = RepoRVAdapter(presenter.userRepoListPresenter)
        viewBinding.rvRepos.adapter = adapter
    }

    override fun updateRepoList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onLoadingRepoListError(throwable: Throwable) {
        Toast.makeText(
            context, "Error occurred while loading repo list: $throwable", Toast.LENGTH_SHORT
        ).show()
    }
}