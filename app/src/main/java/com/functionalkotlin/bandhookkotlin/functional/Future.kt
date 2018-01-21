// Copyright © FunctionalKotlin.com 2017. All rights reserved.

package com.functionalkotlin.bandhookkotlin.functional

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

typealias FutureTask<A> = Deferred<A>

class Future<out A>(val task: FutureTask<A>) {
    companion object
}

fun <A> Future.Companion.pure(a: A): Future<A> =
    Future(async(CommonPool) { a })

fun <A> Future.Companion.async(f: () -> A): Future<A> =
    Future(async(CommonPool) { f() })

suspend fun <A> Future<A>.runAsync(onComplete: (A) -> Unit) {
    onComplete(task.await())
}

fun <A> Future<A>.runSync(): A =
    runBlocking { this@runSync.task.await() }

fun <A, B> Future<A>.map(transform: (A) -> B): Future<B> =
    flatMap {
        Future(async(CommonPool) {
            transform(it)
        })
    }

fun <A, B> Future<A>.flatMap(transform: (A) -> Future<B>): Future<B> =
    Future(async(CommonPool) {
        transform(this@flatMap.task.await()).task.await()
    })

fun <A, B> Future<A>.apply(futureAB: Future<(A) -> B>): Future<B> =
    Future(async(CommonPool) {
        val a = this@apply.task.await()
        val ab = futureAB.task.await()

        ab(a)
    })
