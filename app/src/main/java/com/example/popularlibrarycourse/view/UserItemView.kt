package com.example.popularlibrarycourse.view

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}