package com.example.popularlibrarycourse.presenter.repodetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.github.terrakok.cicerone.androidx.FragmentScreen


class RepositoryScreen(
    private val repository: GitHubRepository,
) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        RepositoryFragment.newInstance(repository)
}