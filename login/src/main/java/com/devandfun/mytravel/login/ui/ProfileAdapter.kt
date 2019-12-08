package com.devandfun.mytravel.login.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.devandfun.mytravel.base.ui.lists.BaseAdapter
import com.devandfun.mytravel.base.ui.lists.BaseHolder
import com.devandfun.mytravel.domain.entities.Profile
import com.devandfun.mytravel.login.R
import java.util.*

class ProfileAdapter: BaseAdapter<Profile>() {
    init {
        setItems(arrayListOf(
            Profile("","Some name", Date()),
            Profile("","Some name", Date()),
            Profile("","Some name", Date())
        ))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Profile> {
        return ProfileHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false))
    }
}