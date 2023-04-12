package com.michona.fitify.domain

import com.michona.fitify.domain.data.dtos.LocalExercise
import com.michona.fitify.domain.usecase.BuildExerciseModel
import org.junit.Test

class BuildExerciseModelTest {

    private val buildExerciseModelUseCase = BuildExerciseModel()

    private val mockLocalExercises = listOf(
        LocalExercise(id = "id", packId = "pack_id", instructionHints = listOf("i_1", "i_2"), title = "title"),
        LocalExercise(id = "id_2", packId = "pack_id", instructionHints = listOf("i_1", "i_2"), title = "title"),
        LocalExercise(id = "id_3", packId = "pack_id", instructionHints = listOf("i_1", "i_2"), title = "title"),
        LocalExercise(id = "id_4", packId = "pack_id", instructionHints = listOf("i_1", "i_2"), title = "title"),
        LocalExercise(id = "id_5", packId = "pack_id", instructionHints = listOf("i_1", "i_2"), title = "title"),
        LocalExercise(id = "id_6", packId = "pack_id", instructionHints = listOf("i_1", "i_2"), title = "title"),
    )

    @Test
    fun `check if local exercises are parsed`() {
        val data = buildExerciseModelUseCase(mockLocalExercises, mapOf())
        assert(data.map { it.name } == mockLocalExercises.map { it.title })
        assert(data.map { it.id } == mockLocalExercises.map { it.id })
    }

    /* there should be more tests here to check if instruction mapper works. */
}
