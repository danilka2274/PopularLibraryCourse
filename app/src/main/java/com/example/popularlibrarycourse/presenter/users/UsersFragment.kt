package com.example.popularlibrarycourse.presenter.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.extensions.showSnakeBar
import com.example.popularlibrarycourse.presenter.abs.AbsFragment
import com.example.popularlibrarycourse.presenter.users.adapter.UsersAdapter
import com.example.popularlibrarycourse.scheduler.Schedulers
import com.example.popularlibrarycourse.ui.IBackButtonListener
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentUsersBinding
import javax.inject.Inject


class UsersFragment : AbsFragment(R.layout.fragment_users), IUsersView
{
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var Repository: IRepository

    @Inject
    lateinit var router: Router

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            repository = Repository,
            router = router,
            schedulers = schedulers
        )
    }
    var usersAdapter: UsersAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        usersAdapter = UsersAdapter(presenter = presenter.usersListPresenter)
        vb.rvUsers.adapter = usersAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.users_title)
    }

    override fun updateList() {
        usersAdapter?.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }

}