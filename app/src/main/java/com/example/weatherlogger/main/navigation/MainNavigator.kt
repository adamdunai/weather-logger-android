package com.example.weatherlogger.main.navigation

import android.content.Context
import com.example.weatherlogger.common.navigation.BaseNavigator
import com.example.weatherlogger.main.MainActivity
import com.example.weatherlogger.main.ui.LogListFragmentDirections
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MainNavigator @Inject constructor(@ActivityContext context: Context) :
    BaseNavigator(context as MainActivity) {

    fun navigateToDetails(logId: Long) {
        navigate(
            LogListFragmentDirections.actionLogListFragmentToDetailsFragment(logId)
        )
    }
}
