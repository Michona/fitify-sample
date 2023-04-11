package com.michona.fitify.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO:

@Composable
fun TitledTopBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}
