package com.example.popularlibrarycourse.presenter.repodetail

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import com.example.popularlibrarycourse.domain.model.GitHubRepository


@SingleState
interface IRepositoryView : MvpView {

    /**
     * Отобразить информацию репозитория
     * @param repo GitHubRepository
     */
    fun showDetail(repo: GitHubRepository)

    /**
     * Отобразить сообщение
     * @param message Текст сообщения
     */
    fun showMessage(message: String)

    /**
     * Установить заголовок
     * @param title Текст заголовка
     */
    fun setTitle(title: String)
}