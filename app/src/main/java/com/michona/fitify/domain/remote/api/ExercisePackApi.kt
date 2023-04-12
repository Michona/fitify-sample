package com.michona.fitify.domain.remote.api

import com.michona.fitify.domain.data.dtos.ExerciseToolSet
import retrofit2.Response
import retrofit2.http.GET

interface ExercisePackApi {
    /**
     * Fetches all the packs from the server
     *
     * @return [ExerciseToolSet] the data that holds a list of pack data (with it's id)
     * */
    @GET("/exercises/manifest_v6.json")
    suspend fun getPacks(): Response<ExerciseToolSet>
}
