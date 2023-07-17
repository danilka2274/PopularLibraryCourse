package com.example.popularlibrarycourse

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrarycourse.adapters.UsersRVAdapter
import com.example.popularlibrarycourse.databinding.FragmentUsersBinding
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.network.AndroidNetworkStatus
import com.example.popularlibrarycourse.model.repository.RetrofitGithubUserReposList
import com.example.popularlibrarycourse.model.repository.RetrofitGithubUsersRepo
import com.example.popularlibrarycourse.model.repository.RoomRepositoriesCache
import com.example.popularlibrarycourse.model.user.RoomUserCache
import com.example.popularlibrarycourse.presenter.BackButtonListener
import com.example.popularlibrarycourse.presenter.GlideImageLoader
import com.example.popularlibrarycourse.presenter.UsersPresenter
import com.example.popularlibrarycourse.presenter.abs.AbsFragment
import com.example.popularlibrarycourse.utils.ApiHolder
import com.example.popularlibrarycourse.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class UsersFragment
@Inject constructor (
    private val networkStatus: AndroidNetworkStatus
) : AbsFragment(R.layout.fragment_users),
    UsersView, BackButtonListener {

    companion object {
        fun newInstance(
            networkStatus: AndroidNetworkStatus): Fragment = UsersFragment(networkStatus)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: AndroidScreens

    @Inject
    lateinit var mainThread: Scheduler

    private val usersCache: RoomUserCache = RoomUserCache.getInstance()
    private val userReposList = RoomRepositoriesCache.getInstance()

    private val githubRepository = RetrofitGithubUsersRepo(ApiHolder.api, networkStatus, Database.getInstance(), usersCache)

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            mainThread,
            githubRepository,
            RetrofitGithubUserReposList(ApiHolder.api, networkStatus, Database.getInstance(),userReposList),
            router,
            screens
        )
    }

    private var adapter: UsersRVAdapter? = null

    private val viewBinding by viewBinding(FragmentUsersBinding::bind)

    override fun init() {
        viewBinding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        viewBinding.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}