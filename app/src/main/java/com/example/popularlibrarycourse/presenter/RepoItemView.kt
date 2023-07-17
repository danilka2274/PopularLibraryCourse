package com.example.popularlibrarycourse.presenter


import com.example.popularlibrarycourse.view.IItemView


interface RepoItemView: IItemView {
    fun setTitle(text: String)
}