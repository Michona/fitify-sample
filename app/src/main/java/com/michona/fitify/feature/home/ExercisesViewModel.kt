package com.michona.fitify.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michona.fitify.domain.data.Exercise
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExercisesViewModel : ViewModel() {

    var exerciseQuery by mutableStateOf("")
        private set


    // snapshotFlow { query }  !! TODO:

    private val dummy = buildList {
        repeat(40) {
            this.add(Exercise("what"))
        }
    }.toList()
    val data: StateFlow<List<Exercise>> = flowOf(dummy).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // TODO:
    fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)
            _isLoading.value = false
        }
    }

    fun updateQuery(query: String) {
        exerciseQuery = query
    }
}