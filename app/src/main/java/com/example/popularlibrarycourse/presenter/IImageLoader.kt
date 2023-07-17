package com.example.popularlibrarycourse.presenter

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}