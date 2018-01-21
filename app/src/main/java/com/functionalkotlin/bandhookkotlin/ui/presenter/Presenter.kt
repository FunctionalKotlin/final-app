// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus

interface Presenter<out T> {

    val view: T
    val bus: Bus

    fun onResume() {
        bus.register(this)
    }

    fun onPause() {
        bus.unregister(this)
    }
}
