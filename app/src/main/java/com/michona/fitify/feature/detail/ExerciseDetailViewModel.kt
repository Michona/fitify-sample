package com.michona.fitify.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.domain.repository.InstructionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExerciseDetailViewModel(private val exerciseId: ExerciseID, exerciseRepository: ExerciseRepository, instructionRepository: InstructionRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            instructionRepository.loadInstructions()
        }
    }

    val uiModel: StateFlow<ExerciseDetailUIModel> = exerciseRepository.exercises.map {
        it.find { model -> model.id == exerciseId }
    }.map { if (it == null) ExerciseDetailUIModel.Empty else ExerciseDetailUIModel.Loaded(it) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExerciseDetailUIModel.Empty)
}

sealed class ExerciseDetailUIModel {

    object Empty : ExerciseDetailUIModel()

    data class Loaded(val data: ExerciseModel) : ExerciseDetailUIModel()

    val title: String
        get() = if (this is Loaded) this.data.name else ""

    val instruction: String
        get() = if (this is Loaded) this.data.instructionsExpanded else ""
}
