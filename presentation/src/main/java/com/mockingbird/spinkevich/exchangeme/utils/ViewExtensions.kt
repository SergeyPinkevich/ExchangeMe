package com.mockingbird.spinkevich.exchangeme.utils

import android.view.View

fun View.visibleIf(condition: () -> Boolean) {
    if (condition.invoke()) {
        makeVisible()
    } else {
        makeGone()
    }
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}