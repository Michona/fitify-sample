package com.michona.fitify

import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.PackID
import com.michona.fitify.feature.detail.ExerciseDetailViewModel
import com.michona.fitify.feature.home.ExercisesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ExercisesViewModel)
    viewModel { (packId: PackID, exerciseId: ExerciseID) -> ExerciseDetailViewModel(packId, exerciseId) }
}