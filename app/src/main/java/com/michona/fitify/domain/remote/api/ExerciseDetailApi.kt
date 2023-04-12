package com.michona.fitify.domain.remote.api

import com.michona.fitify.domain.data.PackID
import com.michona.fitify.domain.data.dtos.ExerciseDetailContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseDetailApi {
    /**
     * Returns the exercises (with details) from a  specific [PackID].
     *
     * @param packCode the pack code that we get exercises from.
     *
     * @return [ExerciseDetailContainer] the data class that has list of exercise details for that specific pack code.
     * */
    @GET("/exercises/{packCode}/exercises_{packCode}_v5.json")
    suspend fun getExercisesFrom(@Path("packCode") packCode: PackID): Response<ExerciseDetailContainer>
}
