package com.michona.fitify.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.michona.fitify.domain.WhileUiSubscribed
import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.domain.withLoading
import com.michona.fitify.feature.BaseViewModel
import com.michona.fitify.navigation.Destination
import com.michona.fitify.navigation.INavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExercisesViewModel(private val exerciseRepository: ExerciseRepository, private val navigator: INavigator) : BaseViewModel() {

    /**
     * A compose State connected to the SearchBar TextField
     *  */
    var exerciseQuery by mutableStateOf("")
        private set

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiModel: StateFlow<ExercisesUIModel> = combine(exerciseRepository.exercises, _isLoading, snapshotFlow { exerciseQuery }) { exercises, isLoading, query ->
        val filteredExercises = exercises.filter {
            it.name.contains(query, ignoreCase = true)
        }
        ExercisesUIModel(
            isLoading = isLoading,
            state = if (filteredExercises.isEmpty()) ExercisesUIModel.State.Empty else ExercisesUIModel.State.Loaded(data = filteredExercises),
        )
    }.stateIn(viewModelScope, WhileUiSubscribed, ExercisesUIModel.default)

    init {
        refresh()
    }

    /**
     * Tries to reload (re-fetch) from [exerciseRepository]. Called when swipe-to-refresh and on screen start.
     * */
    fun refresh() {
        viewModelScope.launch {
            withLoading(_isLoading) {
                exerciseRepository.fetch()
            }
        }
    }

    fun onDetailClicked(exerciseModel: ExerciseModel) {
        navigateWith(navigator) {
            navigateTo(Destination.ExerciseDetail(exerciseCode = exerciseModel.id))
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
