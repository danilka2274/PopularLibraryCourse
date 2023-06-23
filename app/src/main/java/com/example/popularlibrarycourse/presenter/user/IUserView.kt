package com.example.popularlibrarycourse.presenter.user

import com.example.popularlibrarycourse.domain.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState



@SingleState
interface IUserView : MvpView {

    fun showUser(user: GithubUser)

    fun showMessage(message: String)
}