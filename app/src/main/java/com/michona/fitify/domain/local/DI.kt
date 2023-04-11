package com.michona.fitify.domain.local

import com.michona.fitify.domain.local.api.ExercisesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { FitifyDatabase.build(androidApplication()) }
    single<ExercisesDao> { get<FitifyDatabase>().exercisesDao() }
}
