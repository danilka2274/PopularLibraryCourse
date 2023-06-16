package com.example.popularlibrarycourse.presenter.users

import com.example.popularlibrarycourse.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen {
    fun create() = FragmentScreen { UsersFragment.newInstance() }
}