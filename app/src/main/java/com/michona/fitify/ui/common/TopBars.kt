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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

/**
 * A [TopAppBar] with a simple title and an [Icons.Filled.ArrowBack] for the back button.
 * */
@Composable
fun TitledTopBar(title: String, onBack: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}
