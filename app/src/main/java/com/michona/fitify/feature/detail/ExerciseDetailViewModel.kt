package com.michona.fitify.feature.detail

import androidx.lifecycle.viewModelScope
import com.michona.fitify.domain.WhileUiSubscribed
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.domain.repository.InstructionRepository
import com.michona.fitify.feature.BaseViewModel
import com.michona.fitify.navigation.INavigator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExerciseDetailViewModel(
    private val exerciseId: ExerciseID,
    exerciseRepository: ExerciseRepository,
    instructionRepository: InstructionRepository,
    private val navigator: INavigator,
) : BaseViewModel(), INavigator by navigator {
    init {
        viewModelScope.launch {
            /* everytime we open the detail screen, we try to load instructions */
            instructionRepository.loadInstructions()
        }
    }

    val uiModel: StateFlow<ExerciseDetailUIModel> = exerciseRepository.exercises.map {
        it.find { model -> model.id == exerciseId }
    }.map { if (it == null) ExerciseDetailUIModel.Empty else ExerciseDetailUIModel.Loaded(it) }.stateIn(viewModelScope, WhileUiSubscribed, ExerciseDetailUIModel.Empty)
}

sealed class ExerciseDetailUIModel {

    object Empty : ExerciseDetailUIModel()

    data class Loaded(val data: ExerciseModel) : ExerciseDetailUIModel()

    val title: String
        get() = if (this is Loaded) this.data.name else ""
}
