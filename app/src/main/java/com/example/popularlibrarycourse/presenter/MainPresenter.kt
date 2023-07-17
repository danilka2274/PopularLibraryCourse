package com.example.popularlibrarycourse.presenter

import com.example.popularlibrarycourse.model.network.AndroidNetworkStatus
import com.example.popularlibrarycourse.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val screens: IScreens,
    private val networkStatus: AndroidNetworkStatus,
    private val db: Unit
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users(networkStatus))
    }

    fun backClicked() {
        router.exit()
    }
}