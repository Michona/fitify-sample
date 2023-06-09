package com.michona.fitify.domain.repository

import com.michona.fitify.domain.data.ExerciseModel
import com.michona.fitify.domain.data.dtos.LocalExercise
import com.michona.fitify.domain.local.api.ExercisesDao
import com.michona.fitify.domain.local.onError
import com.michona.fitify.domain.local.onSuccess
import com.michona.fitify.domain.remote.api.ExerciseDetailApi
import com.michona.fitify.domain.remote.api.ExercisePackApi
import com.michona.fitify.domain.usecase.BuildExerciseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Contains logic for fetching/updating exercises. It updates the local database and maps the data classes to the exposed [ExerciseModel].
 * */
class ExerciseRepository(
    private val packApi: ExercisePackApi,
    private val exerciseDetailApi: ExerciseDetailApi,
    private val exercisesDao: ExercisesDao,
    private val buildExerciseModel: BuildExerciseModel,
    private val instructionRepository: InstructionRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    val exercises: Flow<List<ExerciseModel>> = combine(exercisesDao.getAll(), instructionRepository.instructions) { local, instructions ->
        buildExerciseModel(local, instructions)
    }.catch { emit(listOf()) }

    /**
     * Fetches latest data from server and updates the appropriate local database.
     * */
    suspend fun fetch() {
        withContext(dispatcher) {
            try {
                packApi.getPacks().onSuccess {
                    /* fetch all details */
                    val localExercises = it.tools.associateWith { pack ->
                        /* if there are exercises in a pack, we associate it with the pack */
                        exerciseDetailApi.getExercisesFrom(packCode = pack.code).body()?.exercises ?: listOf()
                    }.flatMap { (key, details) ->
                        /* for every detail data, we build a LocalExercise with the appropriate pack id */
                        details.map { detail ->
                            LocalExercise(id = detail.id, packId = key.code, instructionHints = detail.instructions?.hints ?: listOf(), title = detail.title)
                        }
                    }

                    /* save the built exercise data to the local database */
                    exercisesDao.upsertAll(*localExercises.toTypedArray())
                }.onError {
                    Timber.e(it)
                }
            } catch (e: Exception) {
                /* these errors should be handled. */
                Timber.e(e)
            }
        }
    }
}
