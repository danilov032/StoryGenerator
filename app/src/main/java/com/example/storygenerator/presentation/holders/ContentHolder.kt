package com.example.storygenerator.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.storygenerator.domain.modeles.Content
import kotlinx.android.synthetic.main.item_content.view.*

class ContentHolder(private val view: View):RecyclerView.ViewHolder(view) {
    fun bindItems(content: Content) {
        view.content.text =  content.content
    }
}