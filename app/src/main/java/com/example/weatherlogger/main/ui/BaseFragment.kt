package com.example.weatherlogger.main.ui

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.weatherlogger.main.MainActivity

abstract class BaseFragment : Fragment() {

    protected fun setTitle(@StringRes titleResId: Int) {
        getMainActivity().setTitle(titleResId)
    }

    protected fun getMainActivity() = activity as MainActivity
}
