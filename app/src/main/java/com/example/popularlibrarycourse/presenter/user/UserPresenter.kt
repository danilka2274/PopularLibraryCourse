package com.example.popularlibrarycourse.presenter.user


import android.annotation.SuppressLint
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import com.example.popularlibrarycourse.domain.repository.IUsersRepository
import com.example.popularlibrarycourse.scheduler.Schedulers
import java.util.concurrent.TimeUnit

class UserPresenter(
    private val login: String,
    private val router: Router,
    private val repository: IUsersRepository,
    private val schedulers: Schedulers
) :
    MvpPresenter<IUserView>() {

    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        repository
            .userById(login)
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showUser
            ) {
                viewState.showMessage(it.message.toString())
                router.replaceScreen(UsersScreen.create())
            }.addTo(disposables)

        repository
            .repoList(login)
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showRepo
            ) {
                viewState.showMessage(it.message.toString())
                router.replaceScreen(UsersScreen.create())
            }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}