package com.michona.fitify.domain.data.dtos

import com.google.gson.annotations.SerializedName
import com.michona.fitify.domain.data.ExerciseID

/* Network DTOs, should only be used in the network layer. */

data class ExerciseDetail(
    @SerializedName("code")
    val id: ExerciseID,
    @SerializedName("title")
    val title: String,
    @SerializedName("instructions")
    val instructions: Instructions?,
)

data class ExerciseDetailContainer(
    @SerializedName("exercises")
    val exercises: List<ExerciseDetail>,
)
