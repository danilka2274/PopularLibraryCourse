package com.example.popularlibrarycourse.presenter.user

import com.example.popularlibrarycourse.domain.model.GithubUser
import com.example.popularlibrarycourse.ui.UserFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen


class UserScreen(private val user: GithubUser) {
    fun create(): Screen = FragmentScreen { UserFragment.newInstance(user) }
}