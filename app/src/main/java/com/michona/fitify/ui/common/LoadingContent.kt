package com.michona.fitify.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Display an initial empty state or swipe to refresh content.
 *
 * TODO: docs
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadingContent(
    isLoading: Boolean,
    isEmpty: Boolean,
    onRefresh: () -> Unit,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullRefreshState(isLoading, { onRefresh() })

    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState),
    ) {
        if (isEmpty) emptyContent() else content()
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter),
        )
    }
}
