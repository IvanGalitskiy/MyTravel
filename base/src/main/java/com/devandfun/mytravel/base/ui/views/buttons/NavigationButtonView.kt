package com.devandfun.mytravel.base.ui.views.buttons

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.devandfun.mytravel.base.R

class NavigationButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0,
    defRes: Int = 0
) : ImageView(context, attrs, defStyle, defRes) {
    init {
        background = context.getDrawable(R.drawable.bg_round_top)
        setPadding(
            context.resources.getDimensionPixelSize(R.dimen.margin_xlarge),
            context.resources.getDimensionPixelSize(R.dimen.margin_xmedium),
            context.resources.getDimensionPixelSize(R.dimen.margin_xlarge),
            context.resources.getDimensionPixelSize(R.dimen.margin_tiny)
        )
    }
}