package com.devandfun.mytravel.base.navigation

import android.view.View

data class NavigationButton(
    val color: Int,
    val icon: Int,
    val onClickListener: (View) -> Unit
)