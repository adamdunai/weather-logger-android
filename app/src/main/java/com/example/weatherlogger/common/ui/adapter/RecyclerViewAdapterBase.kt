package com.example.weatherlogger.common.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherlogger.common.ui.view.ViewHolder

/**
 * Base adapter for RecyclerView, simplifies [ViewHolder] creation.
 */
abstract class RecyclerViewAdapterBase<T, V : View>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, ViewHolder<V>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<V> =
        ViewHolder(onCreateItemView(parent, viewType))

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V
}
