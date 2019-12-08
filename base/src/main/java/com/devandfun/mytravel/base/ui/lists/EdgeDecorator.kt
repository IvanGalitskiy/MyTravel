package com.synergyworldwide.mobile.ribs.logged_in.menu.view.list

import android.graphics.Rect
import android.view.View
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView

class EdgeDecorator(private val topOffset: Int, private val bottomOffset: Int = topOffset) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemCount = state.itemCount

        val itemPosition = parent.getChildAdapterPosition(view)

        // no position, leave it alone
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        if (itemPosition <= 3) {
            // first item
            outRect.set(
                view.marginLeft,
                topOffset + view.marginTop,
                view.marginRight,
                view.marginBottom
            )
        } else if (itemCount > 0 && itemPosition == itemCount - 1) {
            // last item
            outRect.set(
                view.marginLeft,
                view.marginTop,
                view.marginRight,
                bottomOffset + view.marginBottom
            )
        } else {
            // every other item
            outRect.set(view.marginLeft, view.marginTop, view.marginRight, view.marginBottom)
        }
    }
}