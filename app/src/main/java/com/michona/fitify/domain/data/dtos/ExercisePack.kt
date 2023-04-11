package com.michona.fitify.domain.data.dtos

import com.google.gson.annotations.SerializedName
import com.michona.fitify.domain.data.PackID

/* Network DTOs, should only be used in the network layer. */

data class ExercisePack(
    @SerializedName("code")
    val code: PackID,
    @SerializedName("count")
    val count: Int,
    @SerializedName("size")
    val size: Int,
)

data class ExerciseToolSet(
    @SerializedName("tools")
    val tools: List<ExercisePack>,
)
