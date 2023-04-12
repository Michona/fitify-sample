package com.michona.fitify.base

import ExerciseDetail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.michona.fitify.feature.home.ExercisesHome
import com.michona.fitify.navigation.DeclarativeRoute
import com.michona.fitify.navigation.Destination
import com.michona.fitify.navigation.NavigationActions

@Composable
fun FitifyNavGraph(
    // TODO: group into appstate?
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
) {
    NavHost(
        navController = navController,
        startDestination = navigationActions.startDestination,
    ) {
        composable(DeclarativeRoute.HOME) {
            ExercisesHome(onDetailClicked = {
                navigationActions.navigate(it)
            })
        }
        composable(
            DeclarativeRoute.EXERCISE_DETAIL,
            arguments = listOf(
                navArgument(Destination.ExerciseDetail.ARG_EXERCISE_CODE) { type = NavType.StringType },
            ),
        ) { entry ->
            val exerciseId = entry.arguments?.getString(Destination.ExerciseDetail.ARG_EXERCISE_CODE) ?: ""
            ExerciseDetail(exerciseId, onBack = { navigationActions.navigateBack() })
        }
    }
}
