package com.example.storygenerator.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storygenerator.R
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.holders.ContentHolder

class AdapterContent: RecyclerView.Adapter<ContentHolder>() {
    private val items: MutableList<Content> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content, parent, false)
        return ContentHolder(view)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindItems(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(list: List<Content>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun addItems(list: List<Content>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}