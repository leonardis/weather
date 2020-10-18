package com.leonardis.weather.utils

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.display(show: Boolean) {
    if (show) this.show() else this.hide()
}