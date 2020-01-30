package com.devandfun.mytravel.bottom_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devandfun.mytravel.login.LoginFeature
import kotlinx.android.synthetic.main.fragment_bottom_navigation.view.*

class BottomNavigationFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
        if (savedInstanceState == null){
            startLogin(view.vContent)
        }
        return view
    }

    private fun startLogin(container: ViewGroup){
        val loginStarter = LoginFeature.LoginStarter(
            childFragmentManager,
            container,
            Features.LoginDependecies()
        )
        loginStarter.start()
    }
}