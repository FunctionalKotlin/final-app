// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor.base

interface Interactor {

    operator fun invoke(): Event
}
