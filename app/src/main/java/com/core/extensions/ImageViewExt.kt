package com.core.extensions

import android.widget.ImageView
import com.bookmyroom.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImageFromUrl(urlImage: String) {
    setImageDrawable(null)
    if (urlImage.isNotEmpty()) {
        Glide.with(context)
            .load(urlImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(this)
    }
}