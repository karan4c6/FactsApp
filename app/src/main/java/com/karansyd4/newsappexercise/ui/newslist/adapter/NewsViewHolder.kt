package com.karansyd4.newsappexercise.ui.newslist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.karansyd4.newsappexercise.data.local.model.NewsItem
import com.karansyd4.newsappexercise.util.bindImageFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    /**
     * Called when onBindViewHolder is triggered in RecyclerView adapter
     * The new bind will be used to set the values to display items
     * @param newsEntity contains the values to set in UI
     */
    fun bind(newsItem: NewsItem) {

        txtNewsTitle.text = newsItem.title ?: ""
        txtNewsDesc.text = newsItem.description ?: ""

        when {
            !newsItem.url.isNullOrEmpty() -> {
                for (newItem in newsItem.url) {
                    bindImageFromUrl(imgNews, newsItem.url)
                    break
                }
            }
        }
    }
}