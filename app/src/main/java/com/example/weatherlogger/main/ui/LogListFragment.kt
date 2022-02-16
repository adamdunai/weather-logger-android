package com.example.weatherlogger.main.ui

import android.Manifest
import android.annotation.SuppressLint
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
import com.example.weatherlogger.main.navigation.MainNavigator
import com.example.weatherlogger.main.ui.adapter.LogAdapter
import com.example.weatherlogger.main.ui.viewmodel.LogListViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject

@AndroidEntryPoint
@RuntimePermissions
class LogListFragment : BaseFragment() {

    private lateinit var logAdapter: LogAdapter
    private lateinit var progressDrawable: CircularProgressDrawable
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var cancellationToken: CancellationTokenSource

    private var _binding: FragmentLogListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LogListViewModel by viewModels()

    @Inject
    lateinit var navigator: MainNavigator

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

        logAdapter = LogAdapter(getMainActivity()).apply {
            onItemClickListener = object : LogAdapter.OnItemClickListener {
                override fun onItemClicked(logId: Long) {
                    navigator.navigateToDetails(logId)
                }
            }
        }
        progressDrawable = CircularProgressDrawable(getMainActivity()).apply {
            strokeWidth = 5f
            start()
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getMainActivity())

        with(binding) {
            with(logRecyclerView) {
                layoutManager = LinearLayoutManager(getMainActivity())
                adapter = logAdapter
            }

            saveFloatingActionButton.setOnClickListener {
                fetchWeatherDataWithPermissionCheck()
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

    override fun onStart() {
        super.onStart()
        cancellationToken = CancellationTokenSource()
    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun fetchWeatherData() {
        fusedLocationClient.getCurrentLocation(
            LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY,
            cancellationToken.token
        )
            .addOnSuccessListener { location ->
                viewModel.fetchWeatherData(location.latitude, location.longitude)
            }
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

    // TODO use not deprecated solution
    @Suppress("Deprecation")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    @OnNeverAskAgain(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun onLocationPermissionDenied() {
        Snackbar.make(
            binding.root,
            R.string.log_list_location_permission_denied,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        cancellationToken.cancel()
    }
}
