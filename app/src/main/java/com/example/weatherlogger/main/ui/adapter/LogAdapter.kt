package com.example.weatherlogger.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.weatherlogger.common.ui.adapter.BindableBaseRecycleViewAdapter
import com.example.weatherlogger.common.ui.view.ViewHolder
import com.example.weatherlogger.main.model.LogItemUiModel
import com.example.weatherlogger.main.ui.view.LogItemView

class LogAdapter(private val context: Context) :
    BindableBaseRecycleViewAdapter<LogItemUiModel, LogItemView>(LogDiffUtil) {

    var onItemClickListener: OnItemClickListener? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): LogItemView =
        LogItemView(context)

    override fun onBindViewHolder(holder: ViewHolder<LogItemView>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.view.onItemClickListener = onItemClickListener
    }

    override fun getItemId(position: Int): Long =
        getItem(position).id

    interface OnItemClickListener {
        fun onItemClicked(logId: Long)
    }
}
