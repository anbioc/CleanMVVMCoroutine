package com.aba.core.extension

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun EditText.getFieldTextValue(): String = text.toString()

fun RecyclerView.setOnScrollDirectionListener(onScrollUp: () -> Unit, onScrollDown: () -> Unit) {

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            when {
                dy > 30 -> onScrollUp()
                dy < -30 -> onScrollDown()
                else -> { // do nothing
                }
            }
        }
    })

}