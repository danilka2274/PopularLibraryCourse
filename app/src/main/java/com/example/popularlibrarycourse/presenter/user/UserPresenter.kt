package com.example.popularlibrarycourse.presenter.user

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.example.popularlibrarycourse.scheduler.Schedulers


class UserPresenter(
    private val login: String,
    private val router: Router,
    private val repository: IRepository,
    private val schedulers: Schedulers
) :
    MvpPresenter<IUserView>() {

    private var disposables = CompositeDisposable()


    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        repository
            .fetchUserByLogin(login = login)
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showUser
            ) { throwable ->
                viewState.showMessage(message = throwable.message.toString())
                router.backTo(UsersScreen)
            }.addTo(disposables)

        repository
            .fetchUserRepositoriesByLogin(login = login)
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showRepositories
            ) { throwable ->
                viewState.showMessage(message = throwable.message.toString())
            }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    fun setTitle() {
        viewState.setTitle(login.uppercase())
    }
}