package com.example.popularlibrarycourse.presenter.main

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import java.util.concurrent.TimeUnit
import kotlin.random.Random


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