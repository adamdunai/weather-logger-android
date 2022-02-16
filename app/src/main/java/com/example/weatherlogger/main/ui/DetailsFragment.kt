package com.example.weatherlogger.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
