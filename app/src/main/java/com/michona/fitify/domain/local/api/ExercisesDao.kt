package com.michona.fitify.domain.local.api

import androidx.room.*
import com.michona.fitify.domain.data.dtos.LocalExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {
    @Query("SELECT * FROM exercise")
    fun getAll(): Flow<List<LocalExercise>>

    @Upsert
    suspend fun upsertAll(vararg exercises: LocalExercise)

    @Delete
    suspend fun delete(exercise: LocalExercise)
}
