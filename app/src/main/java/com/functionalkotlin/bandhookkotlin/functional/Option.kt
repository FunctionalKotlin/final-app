// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.functional

/**
 * @author Alejandro Hernández
 */
sealed class Option<out A>

object None : Option<Nothing>()

data class Just<out A>(val value: A) : Option<A>()

fun <A, B> Option<A>.map(transform: (A) -> B): Option<B> =
    flatMap { Just(transform(it)) }

fun <A, B> Option<A>.flatMap(transform: (A) -> Option<B>): Option<B> = when (this) {
    is None -> None
    is Just -> transform(this.value)
}

fun <A> Option<A>.ifPresent(execute: (A) -> Unit) {
    if (this is Just) execute(this.value)
}
