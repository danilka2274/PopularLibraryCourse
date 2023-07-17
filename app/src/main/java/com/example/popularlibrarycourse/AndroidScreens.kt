package com.example.popularlibrarycourse

import com.example.popularlibrarycourse.model.user.GithubUser
import com.example.popularlibrarycourse.model.UserRepo
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.network.AndroidNetworkStatus
import com.example.popularlibrarycourse.presenter.IScreens
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(networkStatus: AndroidNetworkStatus) =
        FragmentScreen { UsersFragment.newInstance(networkStatus) }

    override fun userDetails(user: GithubUser, db: Database) =
        FragmentScreen { UserDetailsFragment.newInstance(user, db, "") }

    override fun repoDetails(user: GithubUser, repo: UserRepo) =
        FragmentScreen { RepoDetailsFragment.newInstance(user, repo) }
}