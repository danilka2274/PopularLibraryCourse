package com.example.popularlibrarycourse.presenter.repodetail

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface IRepositoryView : MvpView {

    fun showDetail(repo: GitHubRepository)

    fun setTitle(title: String)
}