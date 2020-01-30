package com.devandfun.mytravel.login.ui

import android.view.View
import com.devandfun.mytravel.base.ui.lists.BaseHolder
import com.devandfun.mytravel.base.entities.Profile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileHolder(itemView: View): BaseHolder<Profile>(itemView) {
    override fun bind(data: Profile) {
        with(itemView){
            itemView.vProfileNameLbl.text = data.name
            itemView.vProfileNameView.name = data.name
        }
    }
}