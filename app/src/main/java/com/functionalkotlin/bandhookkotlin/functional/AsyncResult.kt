// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.functional

typealias AsyncResult<A, E> = Future<Result<A, E>>

fun <A> A.result(): AsyncResult<A, Nothing> = this
    .let(Result.Companion::pure)
    .let(Future.Companion::pure)

fun <E> E.asError(): AsyncResult<Nothing, E> = Future.pure(Failure(this))

fun <A, B, E> AsyncResult<A, E>.transform(f: (A) -> B): AsyncResult<B, E> = this.map {
    result: Result<A, E> -> result.map(f)
}

infix fun <A, E, B> AsyncResult<A, E>.bind(
    transform: (A) -> AsyncResult<B, E>): AsyncResult<B, E> =
    this.flatMap {
        when (it) {
            is Success -> transform(it.value)
            is Failure -> Future.pure(it)
        }
    }

fun <E, A, B> firstSuccessIn(
    list: List<B>, acc: AsyncResult<A, E>, f: (B) -> AsyncResult<A, E>): AsyncResult<A, E> =
    when {
        list.isEmpty() -> acc
        list.size == 1 -> list.first().let(f)
        else -> list.first().let(f).recoverWith {
            firstSuccessIn(list.tail(), acc, f)
        }
    }

fun <A, E> AsyncResult<A, E>.recoverWith(f: () -> (AsyncResult<A, E>)): AsyncResult<A, E> =
    flatMap {
        when {
            it.isFailure() -> f()
            else -> this
        }
    }
