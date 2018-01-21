// Copyright © FunctionalKotlin.com 2017. All rights reserved.

package com.functionalkotlin.bandhookkotlin.functional

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

sealed class Result<out A, out E> {
    companion object
}

data class Success<out A>(val value: A) : Result<A, Nothing>()

data class Failure<out E>(val error: E) : Result<Nothing, E>()

fun <A> Result.Companion.pure(a: A): Result<A, Nothing> = Success(a)

fun <A, E, B> Result<A, E>.map(transform: (A) -> B): Result<B, E> =
    flatMap { transform(it).let { Success(it) } }

fun <A, E, B> Result<A, E>.flatMap(
    transform: (A) -> Result<B, E>): Result<B, E> = when(this) {
        is Success -> transform(value)
        is Failure -> this
    }

fun <A, B, E> Result<A, E>.apply(
    resultAB: Result<(A) -> B, E>): Result<B, E> =
        flatMap { a -> resultAB.map { it(a) } }

fun <A, E> Result<A, E>.ifSuccess(execute: (A) -> Unit) {
    if (this is Success) execute(this.value)
}

fun <A, E> Result<A, E>.ifFailure(execute: (E) -> Unit) {
    if (this is Failure) execute(this.error)
}

fun <A, E> Result<A, E>.fold(onSuccess: (A) -> Unit, onError: (E) -> Unit) {
    ifSuccess(onSuccess)
    ifFailure(onError)
}

fun <A, E> Result<A, E>.isFailure(): Boolean = this is Failure

fun <A, E> Result<A, E>.isSuccess(): Boolean = this is Success

infix fun <A, B, E> ((A) -> B).map(
    asyncResult: AsyncResult<A, E>): AsyncResult<B, E> =
        asyncResult.map { it.map(this) }

infix fun <A, B, E> AsyncResult<(A) -> B, E>.ap(
    asyncResult: AsyncResult<A, E>): AsyncResult<B, E> =
        Future(async(CommonPool) {
            val resultAB = this@ap.task.await()
            val resultA = asyncResult.task.await()

            resultA.apply(resultAB)
        })

infix fun <A, E, B> AsyncResult<A, E>.bind(
    transform: (A) -> AsyncResult<B, E>): AsyncResult<B, E> =
        this.flatMap {
            when(it) {
                is Success -> transform(it.value)
                is Failure -> Future.pure(it)
            }
        }