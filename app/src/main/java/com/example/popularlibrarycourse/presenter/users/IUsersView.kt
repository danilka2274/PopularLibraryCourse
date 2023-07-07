package com.example.popularlibrarycourse.presenter.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface IUsersView : MvpView {

    /**
     * Инициализация RV
     */
    fun init()

    /**
     * Обновить список. в адаптере вызываем notifyDataSetChanged
     */
    fun updateList()

    /**
     * Отобразить сообщение
     * @param message Текст сообщения
     */
    fun showMessage(message: String)
}