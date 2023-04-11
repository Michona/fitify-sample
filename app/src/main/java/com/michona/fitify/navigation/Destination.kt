package com.michona.fitify.navigation

sealed class Destination(val route: String) {

    object Home : Destination("home")

    // TODO:
    data class ExerciseDetail(val packCode: String, val exerciseCode: String) : Destination("exerciseDetail/?$ARG_PACK_CODE={$packCode}?$ARG_EXERCISE_CODE={$exerciseCode}") {
        companion object {
            const val ARG_PACK_CODE = "packCode"
            const val ARG_EXERCISE_CODE = "exerciseCode"

            val default = ExerciseDetail(ARG_PACK_CODE, ARG_EXERCISE_CODE)
        }
    }
}