package com.example.popularlibrarycourse.presenter.repodetail


import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.example.popularlibrarycourse.domain.model.GitHubRepository


class RepositoryScreen(
    private val repository: GitHubRepository,
) {
    fun create(): Screen = FragmentScreen {
        RepositoryFragment.newInstance(
            repository
        )
    }
}