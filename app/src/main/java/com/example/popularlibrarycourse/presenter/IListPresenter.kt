package com.example.popularlibrarycourse.presenter

import com.example.popularlibrarycourse.view.IItemView
import com.example.popularlibrarycourse.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
interface IRepoListPresenter : IListPresenter<RepoItemView>