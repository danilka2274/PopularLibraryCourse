package com.example.popularlibrarycourse.presenter

import com.example.popularlibrarycourse.model.UserRepo
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.user.GithubUser
import com.example.popularlibrarycourse.view.RepoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoDetailsPresenter (
    private val repo: UserRepo,
    private val user: GithubUser,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRepoDetails(repo)
    }

    fun backPressed(): Boolean {
        router.navigateTo(screens.userDetails(user, Database.getInstance()))
        return true
    }

}