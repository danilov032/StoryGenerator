package com.example.storygenerator.presentation.holders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storygenerator.data.utils.dpToPx
import com.example.storygenerator.presentation.utils.Categories
import kotlinx.android.synthetic.main.item_category.view.*
import android.content.res.TypedArray
import com.example.storygenerator.R

class CategoriesHolder(
    private val view: View,
    private val categoryClickListener: (Categories) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun bindItems(category: Categories) {

        val margin = MARGIN.dpToPx
        val heightDisplay = view.resources.displayMetrics.heightPixels
        val heightElem = (heightDisplay - getToolBarHeight() - getStatusBar() - (COUNT_MARGIN * margin)) / COUNT_ELEMENT

        val layoutParams = view.item.layoutParams as GridLayoutManager.LayoutParams
        layoutParams.bottomMargin = margin / 2
        layoutParams.topMargin = margin / 2
        layoutParams.height = heightElem
        view.item.layoutParams = layoutParams

        view.name_category.text = category.getNameCategory()

        view.item.setOnClickListener {

            categoryClickListener(category)
        }
    }

    private fun getToolBarHeight(): Int {
        val attrs = intArrayOf(R.attr.actionBarSize)
        val ta: TypedArray = view.context.obtainStyledAttributes(attrs)
        val toolBarHeight = ta.getDimensionPixelSize(0, -1)
        ta.recycle()
        return toolBarHeight
    }

    private fun getStatusBar(): Int {
        var statusBarHeight = 0
        val resourceId: Int = view.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = view.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    companion object{
        private const val MARGIN = 8
        private const val COUNT_MARGIN = 8
        private const val COUNT_ELEMENT = 7
    }
}