package com.michona.fitify.domain.remote.api

import com.michona.fitify.domain.data.dtos.ExerciseToolSet
import retrofit2.Response
import retrofit2.http.GET

interface ExercisePackApi {
    @GET("/exercises/manifest_v6.json")
    suspend fun getPacks(): Response<ExerciseToolSet>
}
