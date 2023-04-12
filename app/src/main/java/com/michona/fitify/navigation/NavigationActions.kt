package com.michona.fitify.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

/**
 * Wrapper for functionality on [NavHostController]. Forces use of [Destination].
 * */
class NavigationActions(private val navController: NavHostController) {

    val startDestination: String
        get() = Destination.Home.route

    fun navigate(destination: Destination, builder: NavOptionsBuilder.() -> Unit = {}) = navController.navigate(destination.route, builder)

    fun navigateBack() = navController.popBackStack()
}
