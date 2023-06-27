package com.example.popularlibrarycourse.presenter.repodetail

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RepositoryScreen(private val repo: GitHubRepository) {
    fun create(): Screen = FragmentScreen { RepositoryFragment.newInstance(repo) }
}