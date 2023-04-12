package com.michona.fitify.domain.usecase

import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.data.InstructionMap
import com.michona.fitify.domain.data.dtos.LocalExercise

/**
 * Based on [LocalExercise]s and the translations from [InstructionMap] it builds the [ExerciseModel]s needed for the UI.
 * */
class BuildExerciseModel {

    operator fun invoke(localExercises: List<LocalExercise>, instructions: InstructionMap): List<ExerciseModel> = localExercises.filter { it.title.isNotEmpty() }.map { local ->
        ExerciseModel(
            id = local.id,
            packCode = local.packId,
            instructionsExpanded = local.instructionHints.fold("") { acc, next -> "$acc ${instructions[next]}" },
            name = local.title,
        )
    }
}
