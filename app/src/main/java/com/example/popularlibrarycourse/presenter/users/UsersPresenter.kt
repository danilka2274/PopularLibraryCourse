package com.example.popularlibrarycourse.presenter.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.domain.repository.IRepository
import com.example.popularlibrarycourse.presenter.IUserListPresenter
import com.example.popularlibrarycourse.presenter.user.UserScreen
import com.example.popularlibrarycourse.scheduler.Schedulers
import com.example.popularlibrarycourse.ui.IUserItemView


class UsersPresenter(
    private val repository: IRepository,
    private val router: Router,
    private val schedulers: Schedulers
) :
    MvpPresenter<IUsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setUser(user.login, user.avatar)
        }
    }

    val usersListPresenter = UsersListPresenter()

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        repository
            .fetchUsers()
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, { throwable ->
                viewState.showMessage(throwable.message.toString())
            })
            .addTo(disposables)

        usersListPresenter.itemClickListener = { itemView ->
            Log.d("popLibDEBUG", itemView.toString())
            router.navigateTo(UserScreen(usersListPresenter.users[itemView.pos].login).create())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}