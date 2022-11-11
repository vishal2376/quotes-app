package com.vishal2376.quoteapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal2376.quoteapp.models.Quotes

class QuoteAdapter : ListAdapter<Quotes, QuoteAdapter.QuoteViewHolder>(DiffUtils()) {

    class DiffUtils : DiffUtil.ItemCallback<Quotes>() {
        override fun areItemsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class QuoteViewHolder(itemView: View) : ViewHolder(itemView) {

    }

}