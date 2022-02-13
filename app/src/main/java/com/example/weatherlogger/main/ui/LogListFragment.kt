package com.example.weatherlogger.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.FragmentLogListBinding
import com.example.weatherlogger.main.ui.adapter.LogAdapter

class LogListFragment : BaseFragment() {

    private lateinit var logAdapter: LogAdapter

    private var _binding: FragmentLogListBinding? = null
    private val binding get() = _binding!!

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

        with(binding) {
            with(logRecyclerView) {
                layoutManager = LinearLayoutManager(getMainActivity())
                adapter = logAdapter
            }

            saveFloatingActionButton.setOnClickListener {
                // TODO
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
