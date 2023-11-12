package com.example.utility.constant

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startIntentWithData(noinline extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
}
