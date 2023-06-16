package com.example.popularlibrarycourse.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.presenter.user.IUserView
import com.example.popularlibrarycourse.presenter.user.UserPresenter
import com.example.popularlibrarycourse.ui.extensions.showSnakeBar
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentUserBinding


class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), IUserView {
    companion object {

        private const val ARG_USER = "arg_user"

        fun newInstance(user: GithubUser): Fragment = UserFragment().apply {
            arguments = bundleOf(ARG_USER to user)
        }
    }

    private val vb: FragmentUserBinding by viewBinding()

    private val user: GithubUser? by lazy { arguments?.getParcelable(ARG_USER) }
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            user = user,
            router = router
        )
    }

    override fun showUser(user: GithubUser) {
        vb.userLogin.text = user.login
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }
}