package com.example.popularlibrarycourse.ui

import com.example.popularlibrarycourse.presenter.user.IItemView

interface IUserItemView : IItemView {
    fun setUser(login: String, avatar: String)
}