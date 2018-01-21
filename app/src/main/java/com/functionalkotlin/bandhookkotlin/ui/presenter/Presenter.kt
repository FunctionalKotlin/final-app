// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

interface Presenter<out T> {

    val view: T

    suspend fun onResume() {
    }

}
