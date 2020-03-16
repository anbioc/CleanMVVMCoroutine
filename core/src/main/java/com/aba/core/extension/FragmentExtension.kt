package com.aba.core.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showShortToast(message: String) = context?.apply {
    showToast(duration = Toast.LENGTH_SHORT, message = message)
}

fun Fragment.showLongToast(message: String) = context?.apply {
    showToast(duration = Toast.LENGTH_LONG, message = message)
}
