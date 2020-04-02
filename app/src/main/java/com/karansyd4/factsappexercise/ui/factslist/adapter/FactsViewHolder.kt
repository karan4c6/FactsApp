package com.karansyd4.factsappexercise.ui.factslist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.karansyd4.factsappexercise.R
import com.karansyd4.factsappexercise.data.local.model.FactsItem
import com.karansyd4.factsappexercise.util.bindImageFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.facts_item.*

class FactsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
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
                txtFactTitle.text = context.getString(R.string.no_title)
                txtFactTitle.setTextColor(context.resources.getColor(R.color.grey))
            }
            else -> {
                txtFactTitle.text = factsItem.title
                txtFactTitle.setTextColor(context.resources.getColor(R.color.factTitle))
            }
        }
        when {
            factsItem.description.isEmpty() -> {
                txtFactDesc.text = context.getString(R.string.no_description)
                txtFactDesc.setTextColor(context.resources.getColor(R.color.grey))
            }
            else -> {
                txtFactDesc.text = factsItem.description
                txtFactDesc.setTextColor(context.resources.getColor(android.R.color.black))
            }
        }
        when {
            factsItem.url.isEmpty() -> {
                imgFact.setImageResource(R.drawable.placeholder)
            }
            else -> {
                bindImageFromUrl(imgFact, factsItem.url)
            }
        }
    }
}