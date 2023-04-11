package com.michona.fitify.domain.remote.api

import com.michona.fitify.domain.data.PackID
import com.michona.fitify.domain.data.dtos.ExerciseDetailContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseDetailApi {
    @GET("/exercises/{packCode}/exercises_{packCode}_v5.json")
    suspend fun getExerciseDetail(@Path("packCode") packCode: PackID): Response<ExerciseDetailContainer>
}
