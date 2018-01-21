// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.functional

fun <A> List<A>.tail(): List<A> = this.drop(1)
