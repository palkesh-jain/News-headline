package com.news.headline.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    private val height: Int,
    private val lastChildIndex: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = UnitUtil.dpToPx(10f)
        } else {
            outRect.top = height
        }
        // Setting bottom space for the last card
        if (parent.getChildAdapterPosition(view) == lastChildIndex) {
            outRect.bottom = height
        }

    }

}