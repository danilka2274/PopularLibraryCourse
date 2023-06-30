package com.example.popularlibrarycourse.presenter.user

import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState


@SingleState
interface IUserView : MvpView {

    /**
     * Отобразить данные пользователя
     * @param user GithubUser
     */
    fun showUser(user: GithubUser)

    /**
     * Отобразить сообщение
     * @param message Текст сообщения
     */
    fun showMessage(message: String)

    /**
     * Отобразить список репозиториев в RV
     * @param repos Список репозиториев
     */
    fun showRepo(repos: List<GitHubRepository>)

    /**
     * Установить заголовок
     * @param title Текст заголовка
     */
    fun setTitle(title: String)
}