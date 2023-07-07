package com.example.popularlibrarycourse.presenter.user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.model.GithubUser


@StateStrategyType(AddToEndSingleStrategy::class)
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
     * @param repositories Список репозиториев
     */
    fun showRepositories(repositories: List<GitHubRepository>)

    /**
     * Установить заголовок
     * @param title Текст заголовка
     */
    fun setTitle(title: String)
}