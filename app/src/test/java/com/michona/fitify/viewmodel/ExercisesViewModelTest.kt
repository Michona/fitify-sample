package com.michona.fitify.viewmodel

import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.feature.home.ExercisesUIModel
import com.michona.fitify.feature.home.ExercisesViewModel
import com.michona.fitify.rules.MainCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExercisesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var exercisesViewModel: ExercisesViewModel

    @MockK
    private lateinit var exerciseRepository: ExerciseRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check data from repository`() = runTest(UnconfinedTestDispatcher()) {
        val exerciseMockFlow = MutableStateFlow(listOf<ExerciseModel>())
        every { exerciseRepository.exercises } returns exerciseMockFlow
        coEvery { exerciseRepository.fetch() } just runs

        exercisesViewModel = ExercisesViewModel(exerciseRepository)

        val mockExerciseModels = listOf(
            ExerciseModel(id = "id", packCode = "pack", instructionsExpanded = "instructions", name = "name"),
            ExerciseModel(id = "id", packCode = "pack", instructionsExpanded = "instructions", name = "other_things"),
        )
        exerciseMockFlow.emit(mockExerciseModels)

        var model = exercisesViewModel.uiModel.first()
        assert((model.state as? ExercisesUIModel.State.Loaded)?.data == mockExerciseModels)

        exerciseMockFlow.emit(listOf())
        model = exercisesViewModel.uiModel.first()
        assert(model.state is ExercisesUIModel.State.Empty)
    }

    /* there should be other error cases checked, and potentially the query filer. */
}
