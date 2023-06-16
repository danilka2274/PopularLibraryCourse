package com.example.popularlibrarycourse.ui.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible(visible: () -> Boolean): View {
    visibility = if (visible()) {
        View.VISIBLE
    } else {
        View.GONE
    }
    return this
}

fun View.showSnakeBar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}