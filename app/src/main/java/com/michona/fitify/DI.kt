package com.michona.fitify

import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.PackID
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.domain.usecase.BuildExerciseModel
import com.michona.fitify.feature.detail.ExerciseDetailViewModel
import com.michona.fitify.feature.home.ExercisesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ExercisesViewModel)
    viewModel { (packId: PackID, exerciseId: ExerciseID) -> ExerciseDetailViewModel(packId, exerciseId, get()) }

    // TODO: !!!
    single<CoroutineDispatcher> { Dispatchers.IO }

    singleOf(::ExerciseRepository)
    single { BuildExerciseModel() }
}
