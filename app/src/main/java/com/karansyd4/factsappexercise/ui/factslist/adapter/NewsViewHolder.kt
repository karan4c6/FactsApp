package com.karansyd4.factsappexercise.ui.factslist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.karansyd4.factsappexercise.R
import com.karansyd4.factsappexercise.data.local.model.FactsItem
import com.karansyd4.factsappexercise.util.bindImageFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.facts_item.*

class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    /**
     * Called when onBindViewHolder is triggered in RecyclerView adapter
     * The new bind will be used to set the values to display items
     * @param factItem contains the values to set in UI
     */
    fun bind(factsItem: FactsItem) {

        val context = containerView.context

        when {
            factsItem.title.isEmpty() -> {
                txtNewsTitle.text = context.getString(R.string.no_title)
                txtNewsTitle.setTextColor(context.resources.getColor(R.color.grey))
            }
            else -> {
                txtNewsTitle.text = factsItem.title
                txtNewsTitle.setTextColor(context.resources.getColor(R.color.factTitle))
            }
        }
        when {
            factsItem.description.isEmpty() -> {
                txtNewsDesc.text = context.getString(R.string.no_description)
                txtNewsDesc.setTextColor(context.resources.getColor(R.color.grey))
            }
            else -> {
                txtNewsDesc.text = factsItem.description
                txtNewsDesc.setTextColor(context.resources.getColor(android.R.color.black))
            }
        }
        when {
            factsItem.url.isEmpty() -> {
                imgNews.setImageResource(R.drawable.placeholder)
            }
            else -> {
                bindImageFromUrl(imgNews, factsItem.url)
            }
        }
    }
}