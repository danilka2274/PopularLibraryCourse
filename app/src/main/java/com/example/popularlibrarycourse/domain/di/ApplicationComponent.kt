package com.example.popularlibrarycourse.domain.di

import android.content.Context
import com.example.popularlibrarycourse.App
import com.example.popularlibrarycourse.domain.di.modules.GitHubApiModule
import com.example.popularlibrarycourse.domain.di.modules.GitHubStorageModule
import com.example.popularlibrarycourse.domain.di.modules.ImageConverterModule
import com.example.popularlibrarycourse.domain.di.modules.RepositoryModule
import com.example.popularlibrarycourse.scheduler.Schedulers
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton



@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        RepositoryModule::class,
        GitHubApiModule::class,
        GitHubStorageModule::class,
        ImageConverterModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): ApplicationComponent

    }
}