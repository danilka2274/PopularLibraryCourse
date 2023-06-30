package com.example.popularlibrarycourse

import android.os.Bundle
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrarycourse.App.Navigation.navigatorHolder
import com.example.popularlibrarycourse.App.Navigation.router
import com.example.popularlibrarycourse.databinding.ActivityMainBinding
import com.example.popularlibrarycourse.domain.network.NetworkState
import com.example.popularlibrarycourse.domain.network.NetworkStateObservable
import com.example.popularlibrarycourse.presenter.convert.ConvertScreen
import com.example.popularlibrarycourse.presenter.main.IMainView
import com.example.popularlibrarycourse.presenter.main.MainPresenter
import com.example.popularlibrarycourse.presenter.users.UsersScreen
import com.example.popularlibrarycourse.ui.IBackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.util.concurrent.TimeUnit


class MainActivity : MvpAppCompatActivity(R.layout.activity_main), IMainView {

    private val vb: ActivityMainBinding by viewBinding()

    private val presenter by moxyPresenter { MainPresenter(router) }
    private val navigator = AppNavigator(this, R.id.container)

    private val disposables = CompositeDisposable()

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    @Deprecated("Deprecated in Java")
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

        val connect =
            NetworkStateObservable(this)
                .publish()

        connect.connect()

        disposables +=
            connect.delay(1L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe { onNext(1, it) }
    }

    private fun onNext(no: Int, state: NetworkState) {
        Toast.makeText(this, "$no: NetworkState: $state", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}