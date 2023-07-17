package com.example.popularlibrarycourse.presenter


import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.repository.IGithubUserReposList
import com.example.popularlibrarycourse.model.repository.IGithubUsersRepo
import com.example.popularlibrarycourse.model.user.GithubUser
import com.example.popularlibrarycourse.view.UserItemView
import com.example.popularlibrarycourse.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter


class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val usersRepoList: IGithubUserReposList,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getUsersFromNet()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            getUserRepoList(user)
            router.navigateTo(screens.userDetails(user, Database.getInstance()))
        }
    }

    private val disposableUsersList = CompositeDisposable()

    private fun getUsersFromNet() {
        disposableUsersList.add(
            usersRepo.getUsers()
                .observeOn(uiScheduler)
                .doOnError { println("Error: ${it.message}") }
                .subscribe { users ->
                    onUsersListComplete(users)
                }
        )
    }

    private fun onUsersListComplete(users: List<GithubUser>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        for (user in users) {
            getUserRepoList(user)
        }
        viewState.updateList()
    }

    private fun getUserRepoList(user: GithubUser) {
        disposableUsersList.add(
            usersRepoList.getUserRepoList(user)
                .observeOn(uiScheduler)
                .doOnError { println("Error: ${it.message}") }
                .subscribe()
        )
    }

    fun backPressed(): Boolean {
        router.finishChain()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableUsersList.dispose()
    }
}