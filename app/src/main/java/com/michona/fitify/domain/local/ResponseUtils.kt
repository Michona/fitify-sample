package com.michona.fitify.domain.local

import retrofit2.Response

/**
 * Executes [block] only if the [Response] is success.
 * Use this ONLY if we don't care about the error states.
 * */
inline fun <T : Any> Response<T>.onSuccess(block: (T) -> Unit): Response<T> {
    if (this.isSuccessful && this.body() != null) block(this.body()!!)
    return this
}

/**
 * Executes [block] only if the [Response] is error.
 * */
inline fun <T : Any> Response<T>.onError(block: (String?) -> Unit): Response<T> {
    if (!this.isSuccessful) block(this.message())
    return this
}
