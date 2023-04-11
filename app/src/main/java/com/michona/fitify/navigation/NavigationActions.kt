package com.michona.fitify.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import timber.log.Timber

class NavigationActions(private val navController: NavHostController) {

    val startDestination: String
        get() = Destination.Home.route

    /**
     *
     * */
    fun navigate(destination: Destination, builder: NavOptionsBuilder.() -> Unit = {}) = navController.navigate(destination.route, builder)

    fun navigateBack() {
        Timber.d("${navController.currentDestination}")
        navController.popBackStack()
    }
}