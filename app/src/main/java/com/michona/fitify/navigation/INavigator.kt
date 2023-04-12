package com.michona.fitify.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel

/**
 * Exposes methods for navigating between screen with [Destination] as the driving parameter.
 * Exposes [Channel] of [NavigationIntent] that is consumed in the activity and all the events are executed sequentially,
 * based on the lifecycle of the activity.
 * */
interface INavigator {
    val navigationChannel: Channel<NavigationIntent>

    suspend fun navigateBack(
        destination: Destination? = null,
    )

    fun tryNavigateBack(
        destination: Destination? = null,
    )

    suspend fun navigateTo(
        destination: Destination,
        options: NavOptionsBuilder.() -> Unit = {},
    )

    fun tryNavigateTo(
        destination: Destination,
        options: NavOptionsBuilder.() -> Unit = {},
    )
}

class INavigatorImpl : INavigator {
    override val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    override suspend fun navigateBack(destination: Destination?) {
        navigationChannel.send(NavigationIntent.NavigateBack(destination))
    }

    override fun tryNavigateBack(destination: Destination?) {
        navigationChannel.trySend(NavigationIntent.NavigateBack(destination))
    }

    override suspend fun navigateTo(
        destination: Destination,
        options: NavOptionsBuilder.() -> Unit,
    ) {
        navigationChannel.send(NavigationIntent.NavigateTo(destination, options))
    }

    override fun tryNavigateTo(
        destination: Destination,
        options: NavOptionsBuilder.() -> Unit,
    ) {
        navigationChannel.trySend(NavigationIntent.NavigateTo(destination, options))
    }
}
