package com.devandfun.mytravel.login

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.ui.LoginFragment

object LoginFeature {
    interface Starter {
        val dependencies: Dependencies
        fun start()
    }

    interface Callback {
        fun onLogin(selectedProfile: Profile)
    }

    interface Dependencies {
        fun callback(): Callback
    }

    class LoginStarter(
        private val fragmentManager: FragmentManager,
        private val container: ViewGroup,
        override val dependencies: Dependencies
    ) : Starter {
        override fun start() {
            fragmentManager.beginTransaction()
                .replace(container.id,
                    LoginFragment()
                )
                .commit()
        }
    }
}