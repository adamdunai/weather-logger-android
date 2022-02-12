package com.example.weatherlogger.common.ui.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.weatherlogger.common.ui.view.ViewHolder

/**
 * Base adapter for RecyclerView, simplifies binding on [ViewHolder] items
 */
abstract class BindableBaseRecycleViewAdapter<T, V>(diffUtil: DiffUtil.ItemCallback<T>) :
    RecyclerViewAdapterBase<T, V>(diffUtil)
        where V : View,
              V : BindableBaseRecycleViewAdapter.Bindable<T> {

    override fun onBindViewHolder(holder: ViewHolder<V>, position: Int) {
        holder.view.bind(getItem(position))
    }

    interface Bindable<T> {
        fun bind(model: T)
    }
}
