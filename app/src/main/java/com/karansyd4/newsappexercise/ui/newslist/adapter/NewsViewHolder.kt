package com.karansyd4.newsappexercise.ui.newslist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    /**
     * Called when onBindViewHolder is triggered in RecyclerView adapter
     * The new bind will be used to set the values to display items
     * @param newsEntity contains the values to set in UI
     */
    fun bind(newsItem: NewsItem) {
        tv_title.text = newsItem.title ?: ""
        tv_caption.text = newsItem.byline ?: ""
        var date = newsItem.updatedDate
        date = date.subSequence(0, date.lastIndexOf("-")).toString()
        tv_time.text = DateConverter.getConvertedDate(date)

        if (!newsItem.multimedia.isNullOrEmpty()) {
            for (newItem in newsItem.multimedia) {
                if (newItem.format == "thumbLarge") {
                    bindImageFromUrl(iv_news_item_image, newItem.url)
                    break
                }
            }
        }

        cl_news_item.setOnClickListener {
            val action =
                NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(newsItem.id)
            it.findNavController().navigate(action)
        }
    }
}