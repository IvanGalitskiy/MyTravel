package com.devandfun.mytravel.login.create_account

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.data.ProfileRepository

object CreateProfileFeature {
    interface Starter {
        val dependencies: Dependencies
        fun start()
        fun stop()
    }

    interface Callback {
        fun onProfileWasCreated(selectedProfile: Profile)
    }

    interface Dependencies {
        fun callback(): Callback
        fun profileRepository(): ProfileRepository
    }

    class CreateProfileStarter(
        private val fragmentManager: FragmentManager,
        private val container: ViewGroup,
        override val dependencies: Dependencies
    ) : Starter {
        private var fragment: CreateProfileFragment? = null
        override fun start() {
            fragment = CreateProfileFragment()
            fragmentManager.beginTransaction()
                .replace(
                    container.id,
                    fragment!!,
                    javaClass.canonicalName
                )
                .commit()
            fragment!!.parentDependencies = dependencies
        }

        override fun stop() {
            fragment?.let {
                fragmentManager.beginTransaction()
                    .remove(it)
                    .commit()
            }

        }
    }
}