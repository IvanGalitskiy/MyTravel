package com.devandfun.mytravel.base.ui

import androidx.fragment.app.Fragment
import com.devandfun.mytravel.base.di.Destroyable

abstract class BaseFragment<D: Destroyable>: Fragment(){
    protected abstract val dependencies: D

    override fun onDetach() {
        super.onDetach()
        dependencies.destroy()
    }
}