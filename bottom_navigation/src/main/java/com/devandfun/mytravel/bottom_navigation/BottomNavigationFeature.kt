package com.devandfun.mytravel.bottom_navigation

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

object BottomNavigationFeature {
    interface Starter {
        val dependencies: Dependencies
        fun start()
    }

    interface Callback

    interface Dependencies {
        fun callback(): Callback
    }

    class BottomNavigationStarter(
        private val fragmentManager: FragmentManager,
        private val container: ViewGroup,
        override val dependencies: Dependencies
    ) : Starter {
        override fun start() {
            fragmentManager.beginTransaction()
                .replace(container.id,
                    BottomNavigationFragment()
                )
                .commit()
        }
    }
}