package com.aba.core.util

import android.view.View
import android.widget.ImageView

interface ImageLoader {

    fun loadImage(view: ImageView,
                  placeHolderId: Int,
                  errorPlaceHolderId: Int,
                  imageUrl: String)
}