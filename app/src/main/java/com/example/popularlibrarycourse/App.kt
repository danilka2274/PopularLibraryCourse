package com.example.popularlibrarycourse

import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.popularlibrarycourse.domain.di.DaggerApplicationComponent
import com.example.popularlibrarycourse.scheduler.DefaultSchedulers


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> {
        val appComponent = DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .build()

        val cicerone = Cicerone.create()
        appComponent.withNavigatorHolder(cicerone.getNavigatorHolder())
        appComponent.withRouter(cicerone.router)
        appComponent.withSchedulers(DefaultSchedulers())

        return appComponent
    }
}