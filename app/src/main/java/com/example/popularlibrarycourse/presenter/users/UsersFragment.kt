package com.example.popularlibrarycourse.presenter.users

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.App
import com.example.popularlibrarycourse.ui.IBackButtonListener
import com.example.popularlibrarycourse.domain.repository.UserRepositoryFactory

import com.example.popularlibrarycourse.ui.adapter.UsersAdapter
import com.example.popularlibrarycourse.ui.extensions.showSnakeBar
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentUsersBinding


class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), IUsersView,
    IBackButtonListener {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            UserRepositoryFactory.create(),
            App.router
        )
    }
    var adapter: UsersAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.users_title)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }

    override fun backPressed() = presenter.backPressed()
}