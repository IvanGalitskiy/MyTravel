package com.devandfun.mytravel.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devandfun.mytravel.base.ui.BaseFragment
import com.devandfun.mytravel.login.R
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.synergyworldwide.mobile.ribs.logged_in.menu.view.list.EdgeDecorator
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        view.vProfilesList.layoutManager = layoutManager
        view.vProfilesList.adapter = ProfileAdapter()
        view.vProfilesList.addItemDecoration(
            EdgeDecorator(
                context!!.resources.getDimensionPixelSize(
                    R.dimen.margin_large
                ), 0
            )
        )
        return view
    }
}