// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor.base

interface Bus {
    fun post(event: Any)
    fun register(observer: Any)
    fun unregister(observer: Any)
}
