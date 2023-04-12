package com.michona.fitify.domain.remote

import com.michona.fitify.domain.remote.api.ExerciseDetailApi
import com.michona.fitify.domain.remote.api.ExercisePackApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }

    single<ExercisePackApi> { get<Retrofit>().create(ExercisePackApi::class.java) }
    single<ExerciseDetailApi> { get<Retrofit>().create(ExerciseDetailApi::class.java) }
}

/* This is overly-simplistic way of getting instance of Retrofit. Proper implementation is thread-safe! (this is not) */

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(SERVER_URL_BASE).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

const val SERVER_URL_BASE = "https://static.gofitify.com"
