package com.example.weatherlogger.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.FragmentDetailsBinding
import com.example.weatherlogger.main.model.DetailsUiModel
import com.example.weatherlogger.main.model.DetailsUiState
import com.example.weatherlogger.main.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.details_title)

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { detailsUiState ->
                when (detailsUiState) {
                    DetailsUiState.Error -> showComponents(
                        progressBarVisible = false,
                        errorVisible = true,
                        contentVisible = false
                    )
                    DetailsUiState.Loading -> showComponents(
                        progressBarVisible = true,
                        errorVisible = false,
                        contentVisible = false
                    )
                    is DetailsUiState.Success -> {
                        showComponents(
                            progressBarVisible = false,
                            errorVisible = false,
                            contentVisible = true
                        )
                        initUiFromModel(detailsUiState.data)
                    }
                }
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.getDetails()
        }
    }

    private fun showComponents(
        progressBarVisible: Boolean,
        errorVisible: Boolean,
        contentVisible: Boolean,
    ) {
        with(binding) {
            progressBar.isVisible = progressBarVisible
            errorLinearLayout.isVisible = errorVisible
            contentConstraintLayout.isVisible = contentVisible
        }
    }

    private fun initUiFromModel(model: DetailsUiModel) {
        with(binding) {
            iconImageView.load(model.icon)
            temperatureTextView.text = getString(R.string.details_temperature, model.temperature)
            feelsLikeTextView.text = getString(R.string.details_feels_like, model.feelsLike)
            descriptionTextView.text = model.description
            humidityTextView.text = getString(R.string.details_humidity, model.humidity)
            windSpeedTextView.text = getString(R.string.details_wind_speed, model.windSpeed)
            dateTimeTextView.text = model.dateTime
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
