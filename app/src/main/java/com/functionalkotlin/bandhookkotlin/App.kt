// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin

import android.app.Application
import com.functionalkotlin.bandhookkotlin.di.ApplicationComponent
import com.functionalkotlin.bandhookkotlin.di.ApplicationModule
import com.functionalkotlin.bandhookkotlin.di.DaggerApplicationComponent

class App : Application() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}

