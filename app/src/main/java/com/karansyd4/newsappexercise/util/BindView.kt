package com.karansyd4.newsappexercise.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.karansyd4.newsappexercise.R

private val TAG = "BindView"

fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    imageUrl?.let {
        val image = it.replace("http", "https")
        Glide.with(view.context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.placeholder)
            )
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}