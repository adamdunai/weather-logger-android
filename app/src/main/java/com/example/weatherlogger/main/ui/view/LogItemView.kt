package com.example.weatherlogger.main.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weatherlogger.R
import com.example.weatherlogger.common.ui.adapter.BindableBaseRecycleViewAdapter
import com.example.weatherlogger.databinding.ViewLogItemBinding
import com.example.weatherlogger.main.model.LogItemUiModel

class LogItemView :
    ConstraintLayout,
    BindableBaseRecycleViewAdapter.Bindable<LogItemUiModel> {

    private val binding = ViewLogItemBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    init {
        layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        val padding = resources.getDimension(R.dimen.margin_padding_size_small).toInt()
        setPadding(padding, padding, padding, padding)
    }

    override fun bind(model: LogItemUiModel) {
        with(binding) {
            temperatureTextView.text =
                context.getString(R.string.log_item_temperature, model.temperature)
            feelsLikeTextView.text =
                context.getString(R.string.log_item_feels_like, model.feelsLike)
            descriptionTextView.text = model.description
            humidityTextView.text = context.getString(R.string.log_item_humidity, model.humidity)
            windSpeedTextView.text =
                context.getString(R.string.log_item_wind_speed, model.windSpeed)
            dateTimeTextView.text = model.dateTime
        }
    }
}
