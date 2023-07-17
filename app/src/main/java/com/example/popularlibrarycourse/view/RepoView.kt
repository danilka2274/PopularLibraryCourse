package com.example.popularlibrarycourse.view

import com.example.popularlibrarycourse.model.UserRepo
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState


interface RepoView : MvpView {
    @SingleState
    fun setRepoDetails (repo: UserRepo)
}