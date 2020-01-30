package com.devandfun.mytravel.base.navigation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.devandfun.mytravel.base.di.Destroyable
import com.devandfun.mytravel.base.ui.BaseFragment
import com.devandfun.mytravel.base.ui.views.buttons.NavigationButtonView

abstract class BaseNavigationFragment<D : Destroyable> : BaseFragment<D>() {

    lateinit var vMenuContainer: ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vMenuContainer = view.findViewById(getMenuContainer())
        showNavigationButtons()

    }

    abstract fun getMenuContainer(): Int

    open fun getNavigationButtons() = emptyList<NavigationButton>()

    open fun showNavigationButtons(navigationButtons: List<NavigationButton> = getNavigationButtons()) {
        val navigationLayout = LinearLayout(context)
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.BOTTOM
        navigationLayout.layoutParams = layoutParams
        navigationLayout.orientation = LinearLayout.HORIZONTAL
        navigationLayout.gravity = Gravity.CENTER
        context?.let { context ->
            navigationButtons.forEach { navButton ->
                val navButtonView = NavigationButtonView(context)
                navButtonView.setImageResource(navButton.icon)
                navButtonView.backgroundTintList = ColorStateList.valueOf(navButton.color)
                navButtonView.setOnClickListener { navButton.onClickListener.invoke(it) }
                navigationLayout.addView(navButtonView)
            }
        }
        vMenuContainer.addView(navigationLayout, vMenuContainer.childCount)
    }
}