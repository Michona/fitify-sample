package com.michona.fitify.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.michona.fitify.R
import com.michona.fitify.domain.data.Exercise
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.PackID
import com.michona.fitify.ui.common.TitledTopBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ExerciseDetail(packID: PackID, exerciseID: ExerciseID, onBack: () -> Unit, modifier: Modifier = Modifier) {
    val viewModel: ExerciseDetailViewModel = koinViewModel(parameters = { parametersOf(packID, exerciseID) })
    val data = viewModel.data.collectAsStateWithLifecycle()
    ExerciseDetail(data = data.value, onBack = onBack, modifier =  modifier)
}

@Composable
fun ExerciseDetail(data: Exercise, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TitledTopBar(title = R.string.app_name, onBack = { onBack() })
    }) {
        Box(modifier = modifier.padding(it)) {
            Text(text = data.name, modifier = Modifier.align(Alignment.Center))
        }
    }
}

//@Composable
//fun ExerciseDetail(onBack: () -> Unit, modifier: Modifier = Modifier) {
//    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colors.primary)) {
//        Text(text = "WHAT", modifier = Modifier.align(Alignment.Center))
//    }
//}

