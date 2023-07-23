package com.example.quoteapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(val context: Activity, private val quoteList: List<Quote>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {
    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView)  {
        var quote: TextView
        var author: TextView
        var idNo: TextView

        init {
            quote = itemView.findViewById(R.id.textQuote)
            author = itemView.findViewById(R.id.textAuthor)
            idNo = itemView.findViewById(R.id.textId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
            return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentQuote = quoteList[position]
        holder.quote.text = currentQuote.quote
        holder.author.text = currentQuote.author
        holder.idNo.text = currentQuote.id.toString()
    }
}