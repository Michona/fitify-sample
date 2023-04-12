package com.michona.fitify.navigation

import androidx.navigation.NavOptionsBuilder

sealed class NavigationIntent {
    data class NavigateBack(
        val destination: Destination? = null,
    ) : NavigationIntent()

    data class NavigateTo(
        val destination: Destination,
        val options: NavOptionsBuilder.() -> Unit,
    ) : NavigationIntent()
}
