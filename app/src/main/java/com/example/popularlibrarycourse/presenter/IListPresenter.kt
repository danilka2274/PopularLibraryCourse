package com.example.popularlibrarycourse.presenter


import com.example.popularlibrarycourse.presenter.user.IItemView


interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}