package com.michona.fitify.feature.detail

import androidx.lifecycle.ViewModel
import com.michona.fitify.domain.data.Exercise
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.PackID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExerciseDetailViewModel(private val packId: PackID, private val exerciseId: ExerciseID) : ViewModel() {

    private val _data: MutableStateFlow<Exercise> = MutableStateFlow(Exercise("HEEEY"))
    val data: StateFlow<Exercise> = _data.asStateFlow()

    init {
        // TODO: HERE we calculate the model
        _data.value = Exercise("pack: $packId --- ex: $exerciseId")
    }
}