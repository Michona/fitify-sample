package com.michona.fitify

import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.domain.repository.InstructionRepository
import com.michona.fitify.domain.usecase.BuildExerciseModel
import com.michona.fitify.feature.detail.ExerciseDetailViewModel
import com.michona.fitify.feature.home.ExercisesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.onClose

val appModule = module {
    viewModelOf(::ExercisesViewModel)
    viewModel { (exerciseId: ExerciseID) -> ExerciseDetailViewModel(exerciseId, get(), get()) }

    // domain
    single { InstructionRepository(androidContext(), get()) } onClose { it?.close() }
    singleOf(::ExerciseRepository)
    singleOf(::BuildExerciseModel)
}

val dispatchersKoinModule = module {
    single { Dispatchers.IO }
}
