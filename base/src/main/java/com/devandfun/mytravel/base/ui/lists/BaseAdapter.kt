package com.devandfun.mytravel.base.ui.lists

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseHolder<T>>() {
    private val items = ArrayList<T>()

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun setItems(newItems:Collection<T>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}