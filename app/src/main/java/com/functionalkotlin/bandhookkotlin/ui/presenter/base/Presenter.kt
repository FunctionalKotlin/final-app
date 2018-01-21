// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter.base

interface Presenter<out T> {

    val view: T

    suspend fun onResume() {
    }

}
