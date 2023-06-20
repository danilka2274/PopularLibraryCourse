package com.example.popularlibrarycourse.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.domain.repository.MockUsersRepositoryImpl
import com.example.popularlibrarycourse.domain.repository.UserRepositoryFactory
import com.example.popularlibrarycourse.presenter.user.IUserView
import com.example.popularlibrarycourse.presenter.user.UserPresenter
import com.example.popularlibrarycourse.ui.extensions.showSnakeBar
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.FragmentUserBinding


class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), IUserView {
    companion object {

        private const val ARG_USER = "arg_user"
        private const val ERROR_VALUE = -1

        fun newInstance(userId: Int?): Fragment = UserFragment().apply {
            arguments = bundleOf(ARG_USER to userId)
        }
    }

    private val vb: FragmentUserBinding by viewBinding()

    private val userId: Int? by lazy { arguments?.getInt(ARG_USER) }
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userId = userId ?: ERROR_VALUE, //userId = -1 ?: -1,
            router = router,
            UserRepositoryFactory.create()
        )
    }

    override fun showUser(user: GithubUser) {
        vb.userLogin.text = user.login
    }

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }
}