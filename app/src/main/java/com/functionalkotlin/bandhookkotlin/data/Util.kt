// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.functional.Future
import com.functionalkotlin.bandhookkotlin.functional.Result
import com.functionalkotlin.bandhookkotlin.functional.async
import com.functionalkotlin.bandhookkotlin.functional.pure
import retrofit2.Call

inline fun <T, U> Call<T>.unwrapCall(f: T.() -> U) = execute().body()?.f()

fun <A> Call<A>.asyncResult() = Future.async {
    Result.pure(execute().body())
}
