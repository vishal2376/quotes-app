package com.vishal2376.quoteapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal2376.quoteapp.R
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item,parent,false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.content.text = getItem(position).content
        holder.author.text = getItem(position).author
        holder.id.text = getItem(position).quoteId.toString()
    }

    class QuoteViewHolder(itemView: View) : ViewHolder(itemView) {
        val content = itemView.findViewById<TextView>(R.id.tvContent)
        val author = itemView.findViewById<TextView>(R.id.tvAuthor)
        val id = itemView.findViewById<TextView>(R.id.tvQuoteId)
    }

}