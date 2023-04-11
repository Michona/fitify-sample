package com.michona.fitify.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

// TODO:

@Composable
fun TitledTopBar(@StringRes title: Int, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = title))
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}