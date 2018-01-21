// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.functional.AsyncResult
import com.functionalkotlin.bandhookkotlin.functional.Future
import com.functionalkotlin.bandhookkotlin.functional.Result
import com.functionalkotlin.bandhookkotlin.functional.asError
import com.functionalkotlin.bandhookkotlin.functional.async
import com.functionalkotlin.bandhookkotlin.functional.bind
import com.functionalkotlin.bandhookkotlin.functional.pure
import com.functionalkotlin.bandhookkotlin.functional.result
import retrofit2.Call

inline fun <T, U> Call<T>.unwrapCall(f: T.() -> U) = execute().body()?.f()

fun <A, B> Call<A>.asyncResult(f: A.() -> B?): AsyncResult<B?, Nothing> =
    Future.async { Result.pure(execute().body()?.f()) }

fun <A, E> AsyncResult<A?, Nothing>.orElse(f: () -> E): AsyncResult<A, E> {
    return bind {
        it?.result() ?: f().asError()
    }
}
