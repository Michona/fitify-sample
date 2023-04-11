package com.michona.fitify.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.data.PackID
import com.michona.fitify.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class ExerciseDetailViewModel(private val packId: PackID, private val exerciseId: ExerciseID, private val exerciseRepository: ExerciseRepository) : ViewModel() {

    val uiModel: StateFlow<ExerciseModel?> =
        exerciseRepository.exercises.map {
            Timber.d("$packId ---- $exerciseId")
            Timber.d("${it.size}")
            it.find { model ->
                model.id == exerciseId
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}
