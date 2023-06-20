package com.example.popularlibrarycourse.presenter.user


import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import com.example.popularlibrarycourse.domain.repository.IUsersRepository
import com.example.popularlibrarycourse.domain.repository.MockUsersRepositoryImpl
import com.example.popularlibrarycourse.domain.repository.UserRepositoryFactory
import com.example.popularlibrarycourse.presenter.users.UsersScreen

class UserPresenter(
    private val userId: Int,
    private val router: Router,
    private val repository: IUsersRepository,
) :
    MvpPresenter<IUserView>() {

    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        repository
            .userById(userId)
            .subscribe(
                viewState::showUser
            ) {
                viewState.showMessage(it.message.toString())
                router.replaceScreen(UsersScreen.create())
            }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}