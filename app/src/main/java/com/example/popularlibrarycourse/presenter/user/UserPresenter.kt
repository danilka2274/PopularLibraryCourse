package com.example.popularlibrarycourse.presenter.user

import android.os.Handler
import android.os.Looper
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val user: GithubUser?,
    private val router: Router
) :
    MvpPresenter<IUserView>() {

    companion object {
        const val CLOSE_DELAY = 800L
        const val ERROR_MESSAGE = "Не выбран пользователь!"
    }

    override fun onFirstViewAttach() {
        if (user != null) {
            viewState.showUser(user)
        } else {
            viewState.showMessage(ERROR_MESSAGE)
            Thread {
                Handler(Looper.getMainLooper()).postDelayed({
                    router.replaceScreen(UsersScreen.create())
                }, CLOSE_DELAY)
            }.start()
        }
    }
}