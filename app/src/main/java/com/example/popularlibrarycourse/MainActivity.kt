package com.example.popularlibrarycourse

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrarycourse.databinding.ActivityMainBinding
import com.example.popularlibrarycourse.model.database.Database
import com.example.popularlibrarycourse.model.network.AndroidNetworkStatus
import com.example.popularlibrarycourse.presenter.BackButtonListener
import com.example.popularlibrarycourse.presenter.MainPresenter
import com.example.popularlibrarycourse.presenter.abs.AbsActivity
import com.example.popularlibrarycourse.view.MainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : AbsActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var screens: AndroidScreens

    @Inject
    lateinit var networkStatus: AndroidNetworkStatus

    private val presenter by moxyPresenter {
        MainPresenter(
            router,
            screens,
            networkStatus,
            Database.create(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidNetworkStatus(this)
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

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
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}