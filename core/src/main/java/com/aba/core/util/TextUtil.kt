package com.aba.core.util

import android.text.Html

object TextUtil {
    fun trimHTML(input: String): String = Html.fromHtml(input).toString().trim()
}