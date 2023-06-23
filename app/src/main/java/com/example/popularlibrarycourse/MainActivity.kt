package com.example.popularlibrarycourse

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.App.Navigation.navigatorHolder
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.presenter.convert.ConvertScreen
import com.example.popularlibrarycourse.presenter.main.IMainView
import com.example.popularlibrarycourse.presenter.main.MainPresenter
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.example.popularlibrarycourse.ui.IBackButtonListener
import com.example.popularlibrarycourse.R
import com.example.popularlibrarycourse.databinding.ActivityMainBinding


class MainActivity : MvpAppCompatActivity(R.layout.activity_main), IMainView {

    private val vb: ActivityMainBinding by viewBinding()

    private val presenter by moxyPresenter { MainPresenter(router) }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.back()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        vb.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_users -> {
                    router.replaceScreen(UsersScreen.create())
                    true
                }
                R.id.navigation_convert -> {
                    router.replaceScreen(ConvertScreen().create())
                    true
                }
                else -> false
            }
        }

        router.replaceScreen(UsersScreen.create())
    }

}