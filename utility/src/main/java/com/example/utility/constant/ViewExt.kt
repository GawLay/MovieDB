package com.example.utility.constant

import android.view.View

fun View.visibleExt() {
    visibility = View.VISIBLE
}

fun View.invisibleExt() {
    visibility = View.INVISIBLE
}

fun View.goneExt() {
    visibility = View.GONE
}