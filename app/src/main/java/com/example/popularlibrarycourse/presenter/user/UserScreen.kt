package com.example.popularlibrarycourse.presenter.user


import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen


class UserScreen(private val login: String) {
    fun create(): Screen = FragmentScreen { UserFragment.newInstance(login = login) }
}