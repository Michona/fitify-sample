package com.michona.fitify.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted

private const val StopTimeoutMillis: Long = 5000

/**
 * A [SharingStarted] meant to be used with a [StateFlow] to expose data to the UI.
 */
val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(StopTimeoutMillis)

/**
 * Calls the block sandwiched between [isLoading] flow. Used when we have a simple isLoading logic near ui.
 * */
inline fun withLoading(isLoading: MutableStateFlow<Boolean>, block: () -> Unit) {
    isLoading.value = true
    block()
    isLoading.value = false
}
