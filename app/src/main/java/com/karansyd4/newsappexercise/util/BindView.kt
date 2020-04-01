package com.karansyd4.newsappexercise.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.karansyd4.newsappexercise.R


fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    when {
        !imageUrl.isNullOrEmpty() -> {
            Glide.with(view.context)
                .setDefaultRequestOptions(
                    RequestOptions().placeholder(R.color.colorAccent).error(
                        R.color.colorAccent
                    )
                )
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
        else -> Glide.with(view.context)
            .load(R.drawable.google_news)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}