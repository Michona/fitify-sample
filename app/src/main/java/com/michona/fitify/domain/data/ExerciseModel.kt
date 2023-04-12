package com.michona.fitify.domain.data

import com.michona.fitify.domain.remote.SERVER_URL_BASE

/**
 * Close-to-UI model. Holds data that's used when building the list of Exercises as well as the detail.
 * Contains fields that are more UI-centric.
 * */
data class ExerciseModel(val id: ExerciseID, val packCode: PackID, val instructionsExpanded: String, val name: String) {

    val thumbnailUrl: String
        get() = "$SERVER_URL_BASE/exercises/$packCode/thumbnails/$id.jpg"

    val instructionVideoUrl: String
        get() = "$SERVER_URL_BASE/exercises/$packCode/videos/$id.mp4"
}
