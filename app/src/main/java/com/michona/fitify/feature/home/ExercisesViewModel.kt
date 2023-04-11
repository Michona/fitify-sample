package com.michona.fitify.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExercisesViewModel(private val repository: ExerciseRepository) : ViewModel() {

    var exerciseQuery by mutableStateOf("")
        private set

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiModel: StateFlow<ExercisesUIModel> = combine(repository.exercises, _isLoading, snapshotFlow { exerciseQuery }) { exercises, isLoading, query ->
        val filteredExercises = exercises.filter {
            it.name.contains(query, ignoreCase = true)
        }
        ExercisesUIModel(
            isLoading = isLoading,
            state = if (filteredExercises.isEmpty()) ExercisesUIModel.State.Empty else ExercisesUIModel.State.Loaded(data = filteredExercises),
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExercisesUIModel.default)

    fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.fetch()
            _isLoading.value = false
        }
    }

    fun updateQuery(query: String) {
        exerciseQuery = query
    }
}

/**
 *
 * */
data class ExercisesUIModel(val state: State, val isLoading: Boolean) {

    sealed class State {
        abstract val data: List<ExerciseModel>

        object Empty : State() {
            override val data: List<ExerciseModel> = listOf()
        }

        data class Loaded(override val data: List<ExerciseModel>) : State()

        data class Error(val message: String) : State() {
            override val data: List<ExerciseModel> = listOf()
        }
    }

    companion object {
        val default = ExercisesUIModel(state = State.Empty, isLoading = false)
    }
}
