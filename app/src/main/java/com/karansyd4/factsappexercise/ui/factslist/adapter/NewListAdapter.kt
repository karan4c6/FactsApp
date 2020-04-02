package com.karansyd4.factsappexercise.ui.factslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.karansyd4.factsappexercise.R
import com.karansyd4.factsappexercise.data.local.model.FactsItem

class NewListAdapter : ListAdapter<FactsItem, NewsViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.facts_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class DiffCallback : DiffUtil.ItemCallback<FactsItem>() {
    override fun areItemsTheSame(oldItem: FactsItem, newItem: FactsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FactsItem, newItem: FactsItem): Boolean {
        return oldItem == newItem
    }
}