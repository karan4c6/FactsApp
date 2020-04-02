package com.karansyd4.factsappexercise.ui.factslist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
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

        txtNewsTitle.text = factsItem.title ?: ""
        txtNewsDesc.text = factsItem.description ?: ""

        when {
            !factsItem.url.isNullOrEmpty() -> {
                for (newItem in factsItem.url) {
                    bindImageFromUrl(imgNews, factsItem.url)
                    break
                }
            }
        }
    }
}