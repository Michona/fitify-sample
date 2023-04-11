package com.michona.fitify.domain.usecase

import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.data.dtos.LocalExercise

/**
 * Based on [LocalExercise]s and the translations from [InstructionsRepository] it builds the [ExerciseModel]s needed for the UI.
 * */
class BuildExerciseModel() {

    suspend operator fun invoke(localExercises: List<LocalExercise>): List<ExerciseModel> = localExercises.filter { it.title.isNotEmpty() }.map { local ->
        ExerciseModel(
            id = local.id,
            packCode = local.packId,
            instructionsExpanded = "", // TODO:
            name = local.title,
        )
    }

    // TODO: sort?
}
