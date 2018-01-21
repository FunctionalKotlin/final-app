// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.util

import com.functionalkotlin.bandhookkotlin.functional.AsyncResult
import com.functionalkotlin.bandhookkotlin.functional.Failure
import com.functionalkotlin.bandhookkotlin.functional.Success
import com.functionalkotlin.bandhookkotlin.functional.isFailure
import com.functionalkotlin.bandhookkotlin.functional.isSuccess
import com.functionalkotlin.bandhookkotlin.functional.runSync
import io.kotlintest.matchers.shouldBe

fun <A, E> AsyncResult<A, E>.asSuccess(f: A.() -> Unit) {
    val result = runSync()
    result.isSuccess() shouldBe true
    f((result as Success).value)
}

fun <A, E> AsyncResult<A, E>.asFailure(f: E.() -> Unit) {
    val result = runSync()
    result.isFailure() shouldBe true
    f((result as Failure).error)
}