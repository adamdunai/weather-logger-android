package com.example.weatherlogger.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.FragmentLogListBinding
import com.example.weatherlogger.main.model.LogItemUiModel
import com.example.weatherlogger.main.model.LogListUiState
import com.example.weatherlogger.main.ui.adapter.LogAdapter
import com.example.weatherlogger.main.ui.viewmodel.LogListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogListFragment : BaseFragment() {

    private lateinit var logAdapter: LogAdapter
    private lateinit var progressDrawable: CircularProgressDrawable

    private var _binding: FragmentLogListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.log_list_title)

        logAdapter = LogAdapter(getMainActivity())
        progressDrawable = CircularProgressDrawable(getMainActivity()).apply {
            strokeWidth = 5f
            start()
        }

        with(binding) {
            with(logRecyclerView) {
                layoutManager = LinearLayoutManager(getMainActivity())
                adapter = logAdapter
            }

            saveFloatingActionButton.setOnClickListener {
                fetchWeatherData()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherLogFlow.collectLatest(::updateLogRecyclerViewItem)
            }
        }

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    LogListUiState.Error -> {
                        Snackbar.make(
                            binding.root,
                            R.string.log_list_error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                        extendSaveFab()
                    }
                    LogListUiState.Loading -> {
                        shrinkSaveFab()
                    }
                    LogListUiState.Success -> {
                        extendSaveFab()
                    }
                }
            }
        }
    }

    private fun fetchWeatherData() {
        // TODO
        viewModel.fetchWeatherData(47.5559396, 19.0166011)
    }

    private fun updateLogRecyclerViewItem(newList: List<LogItemUiModel>) {
        logAdapter.submitList(newList) {
            binding.logRecyclerView.scrollToPosition(0)
        }
    }

    private fun extendSaveFab() {
        with(binding.saveFloatingActionButton) {
            setIconResource(R.drawable.ic_baseline_save_24)
            setText(R.string.log_list_save_button)
            extend()
            isClickable = true
        }

        binding.saveFloatingActionButton
    }

    private fun shrinkSaveFab() {
        with(binding.saveFloatingActionButton) {
            icon = progressDrawable
            shrink()
            isClickable = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
