// Copyright © FunctionalKotlin.com 2017. All rights reserved.

package com.functionalkotlin.bandhookkotlin.functional

sealed class Result<out A, out E> {
    companion object
}

data class Success<out A>(val value: A) : Result<A, Nothing>()

data class Failure<out E>(val error: E) : Result<Nothing, E>()

fun <A> Result.Companion.pure(a: A): Result<A, Nothing> = Success(a)

fun <A, E, B> Result<A, E>.map(transform: (A) -> B): Result<B, E> =
    flatMap { transform(it).let { Success(it) } }

fun <A, E, B> Result<A, E>.flatMap(
    transform: (A) -> Result<B, E>): Result<B, E> = when (this) {
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
