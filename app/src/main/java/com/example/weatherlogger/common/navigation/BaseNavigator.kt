package com.example.weatherlogger.common.navigation

import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.weatherlogger.R
import com.example.weatherlogger.main.MainActivity

abstract class BaseNavigator(mainActivity: MainActivity) {

    private val navController by lazy {
        Navigation.findNavController(mainActivity, R.id.fragmentNavHost)
    }

    protected fun navigate(direction: NavDirections) {
        navController.navigate(direction)
    }
}
