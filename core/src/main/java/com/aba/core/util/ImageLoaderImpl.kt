package com.aba.core.util

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor() : ImageLoader {
    override fun loadImage(
        view: ImageView,
        placeHolderId: Int,
        errorPlaceHolderId: Int,
        imageUrl: String
    ) {
        try {
            loadImageCareLess(
                view,
                placeHolderId,
                errorPlaceHolderId,
                imageUrl
            )
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    private fun loadImageCareLess(
        view: ImageView,
        placeHolderId: Int,
        errorPlaceHolderId: Int,
        imageUrl: String
    ) {
        Picasso.get().load(imageUrl)
            .placeholder(placeHolderId)
            .error(errorPlaceHolderId)
            .into(view)
    }

}