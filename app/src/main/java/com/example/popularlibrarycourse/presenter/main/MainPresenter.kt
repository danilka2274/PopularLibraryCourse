package com.example.popularlibrarycourse.presenter.main

import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class MainPresenter(
    private val router: Router,
) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen.create())
    }

    fun back() = router.exit()
}