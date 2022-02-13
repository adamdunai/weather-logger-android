package com.example.weatherlogger.main.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherlogger.main.model.LogItemUiModel

object LogDiffUtil : DiffUtil.ItemCallback<LogItemUiModel>() {

    override fun areItemsTheSame(oldItem: LogItemUiModel, newItem: LogItemUiModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LogItemUiModel, newItem: LogItemUiModel): Boolean =
        oldItem == newItem
}
