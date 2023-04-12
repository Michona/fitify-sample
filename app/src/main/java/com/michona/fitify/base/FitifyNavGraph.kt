package com.michona.fitify.base

import ExerciseDetail
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.michona.fitify.feature.home.ExercisesHome
import com.michona.fitify.navigation.DeclarativeRoute
import com.michona.fitify.navigation.Destination
import com.michona.fitify.navigation.INavigator
import com.michona.fitify.navigation.NavigationIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.compose.get

/**
 * Builds the top level [NavHost] and defines all the routes for the [Destination]s
 * */
@Composable
fun FitifyNavGraph(
    navController: NavHostController = rememberNavController(),
    navigator: INavigator = get(),
) {
    NavigationEffects(navigationChannel = navigator.navigationChannel, navHostController = navController)

    NavHost(
        navController = navController,
        startDestination = DeclarativeRoute.HOME,
    ) {
        composable(DeclarativeRoute.HOME) {
            ExercisesHome()
        }
        composable(
            DeclarativeRoute.EXERCISE_DETAIL,
            arguments = listOf(
                navArgument(Destination.ExerciseDetail.ARG_EXERCISE_CODE) { type = NavType.StringType },
            ),
        ) { entry ->
            val exerciseId = entry.arguments?.getString(Destination.ExerciseDetail.ARG_EXERCISE_CODE) ?: ""
            ExerciseDetail(exerciseId)
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController,
) {
    val activity = (LocalContext.current as? Activity)
    val currentNavBackStackEntry by navHostController.currentBackStackEntryAsState()

    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            if (currentNavBackStackEntry?.getLifecycle()?.currentState != Lifecycle.State.RESUMED) {
                /* don't execute navigation if state is not resumed. */
                return@collect
            }

            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.destination != null) {
                        navHostController.popBackStack(intent.destination.route, true)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.destination.route, intent.options)
                }
            }
        }
    }
}
