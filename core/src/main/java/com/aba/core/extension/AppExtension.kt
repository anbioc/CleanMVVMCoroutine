package com.aba.core.extension

import android.content.Context
import android.widget.Toast
import com.aba.core.util.TextUtil


fun Context.showShortToast(message: String) {
    this.showToast(duration = Toast.LENGTH_SHORT, message = message)
}

fun Context.showLongToast(message: String) {
    this.showToast(duration = Toast.LENGTH_LONG, message = message)
}

fun Context.showToast(duration: Int, message: String) {
    Toast.makeText(this, message, duration).show()
}

fun String.fromHtml() = TextUtil.trimHTML(this)