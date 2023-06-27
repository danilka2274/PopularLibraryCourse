package com.example.popularlibrarycourse.presenter.repodetail

import moxy.MvpPresenter
import com.example.popularlibrarycourse.domain.model.GitHubRepository


class RepositoryPresenter(private val repo: GitHubRepository) : MvpPresenter<IRepositoryView>() {

    override fun onFirstViewAttach() {
        viewState.showDetail(repo)
    }

    fun setTitle() {
        viewState.setTitle(repo.name)
    }
}