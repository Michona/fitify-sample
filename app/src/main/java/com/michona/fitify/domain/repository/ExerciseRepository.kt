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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * TODO: docs
 * */
class ExerciseRepository(
    private val packApi: ExercisePackApi,
    private val exerciseDetailApi: ExerciseDetailApi,
    private val exercisesDao: ExercisesDao,
    private val buildExerciseModel: BuildExerciseModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    /**
     * TODO: docs
     * */
    val exercises: Flow<List<ExerciseModel>> = exercisesDao.getAll().map(buildExerciseModel::invoke).catch { emit(listOf()) }

    /**
     * TODO: docs
     * */
    suspend fun fetch() {
        withContext(dispatcher) {
            packApi.getPacks().onSuccess {
                /* fetch all details */
                val localExercises = it.tools.associateWith { pack ->
                    /* if there are exercises in a pack, we associate it with the pack */
                    exerciseDetailApi.getExerciseDetail(packCode = pack.code).body()?.exercises ?: listOf()
                }.flatMap { (key, details) ->
                    /* for every detail data, we build a LocalExercise with the appropriate pack id */
                    details.map { detail ->
                        LocalExercise(id = detail.code, packId = key.code, instructionHints = detail.instructions?.hints ?: listOf(), title = detail.title)
                    }
                }

                /* save the built exercise data to the local database */
                exercisesDao.upsertAll(*localExercises.toTypedArray())
            }.onError {
                Timber.e(it)
            }
        }
    }
}
