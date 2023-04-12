package com.michona.fitify.navigation

/**
 * Holds data about the destinations that we can reach. It builds the [route] that we use when calling .navigate on the NavController.
 * */
sealed class Destination(
    val route: String,
) {
    object Home : Destination("home")

    data class ExerciseDetail(val exerciseCode: String) : Destination("$BASE/$exerciseCode") {
        companion object {
            const val BASE = "exerciseDetail"
            const val ARG_EXERCISE_CODE = "exerciseCode"
        }
    }
}

/**
 * This is how routes are defined in the NavGraph. They are composed as a url string (same as web)
 * */
object DeclarativeRoute {
    val HOME = Destination.Home.route
    const val EXERCISE_DETAIL = "${Destination.ExerciseDetail.BASE}/{${Destination.ExerciseDetail.ARG_EXERCISE_CODE}}"
}
