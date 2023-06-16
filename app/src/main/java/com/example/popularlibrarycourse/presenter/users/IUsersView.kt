package com.example.popularlibrarycourse.presenter.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface IUsersView : MvpView {
    fun init()
    fun updateList()
}