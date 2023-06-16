package com.example.popularlibrarycourse

import androidx.core.view.ActionProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import com.example.popularlibrarycourse.App.Navigation.navigatorHolder
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.presenter.main.IMainView
import com.example.popularlibrarycourse.presenter.main.MainPresenter
import com.example.popularlibrarycourse.ui.IBackButtonListener
import com.example.popularlibrarycourse.R


class MainActivity : MvpAppCompatActivity(R.layout.activity_main), IMainView {

    private val vb: ActionProvider by viewBinding()

    private val presenter by moxyPresenter { MainPresenter(router) }
    val navigator = AppNavigator(this, R.id.container)

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
}