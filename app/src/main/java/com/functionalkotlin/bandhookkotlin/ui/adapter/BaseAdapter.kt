// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup
import com.functionalkotlin.bandhookkotlin.ui.activity.ViewAnkoComponent
import com.functionalkotlin.bandhookkotlin.ui.adapter.BaseAdapter.BaseViewHolder
import com.functionalkotlin.bandhookkotlin.ui.util.singleClick
import kotlin.properties.Delegates

abstract class BaseAdapter<Item, Component : ViewAnkoComponent<RecyclerView>>(
    private val listener: (Item) -> Unit = {}) : Adapter<BaseViewHolder<Component>>() {

    abstract val bind: Component.(item: Item) -> Unit

    var items: List<Item> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    abstract fun onCreateComponent(parent: RecyclerView): Component

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Component> =
        BaseViewHolder(onCreateComponent(parent as RecyclerView))

    override fun onBindViewHolder(holder: BaseViewHolder<Component>, position: Int) {
        val item = items[position]
        holder.itemView.singleClick { listener(item) }
        holder.ui.bind(item)
    }

    override fun getItemCount() = items.size

    class BaseViewHolder<out Component : ViewAnkoComponent<RecyclerView>>(val ui: Component) :
        RecyclerView.ViewHolder(ui.inflate())
}
