package com.example.storygenerator.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storygenerator.R
import com.example.storygenerator.presentation.holders.CategoriesHolder
import com.example.storygenerator.presentation.utils.Categories

class AdapterCategories(private val CategoryClickListener: (Categories) -> Unit) :
    RecyclerView.Adapter<CategoriesHolder>() {
    private val items: MutableList<Categories> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoriesHolder(view, CategoryClickListener)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(list: List<Categories>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}