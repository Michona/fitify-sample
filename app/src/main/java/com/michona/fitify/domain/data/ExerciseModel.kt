package com.michona.fitify.domain.data

/**
 * Close-to-UI model.
 * TODO: docs
 * */
data class ExerciseModel(val id: ExerciseID, val packCode: PackID, val instructionsExpanded: String, val name: String) {

    val thumbnailUrl: String
        get() = "https://static.gofitify.com/exercises/$packCode/thumbnails/$id.jpg"

    val instructionVideoUrl: String
        get() = ""
}
