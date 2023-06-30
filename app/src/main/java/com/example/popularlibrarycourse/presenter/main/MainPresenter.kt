package com.example.popularlibrarycourse.presenter.main

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter


class MainPresenter(
    private val router: Router,
) : MvpPresenter<IMainView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun back() = router.exit()

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}