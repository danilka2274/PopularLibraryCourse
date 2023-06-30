package com.example.popularlibrarycourse.presenter.repodetail

import android.annotation.SuppressLint
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.presenter.user.UserScreen
import com.example.popularlibrarycourse.scheduler.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter

/****
Project PopularLibrary
Package softing.ubah4ukdev.popularlibrary.presenter.repodetail

Created by Ivan Sheynmaer

2021.08.16
v1.0
 */
class RepositoryPresenter(
    private val userRepository: GitHubRepository,
    private val schedulers: Schedulers,
    private val repository: IRepository
) :
    MvpPresenter<IRepositoryView>() {

    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        repository
            .fetchRepositoryInfo(userRepository.login, userRepository.name)
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showDetail
            ) {
                viewState.showMessage(it.message.toString())
            }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    fun backPressed(): Boolean {
        router.replaceScreen(UserScreen(userRepository.login).create())
        return true
    }

    fun setTitle() {
        viewState.setTitle(userRepository.name)
    }
}