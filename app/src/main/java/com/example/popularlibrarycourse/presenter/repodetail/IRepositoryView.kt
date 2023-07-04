package com.example.popularlibrarycourse.presenter.repodetail

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState


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