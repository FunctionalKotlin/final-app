// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.main

import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.ui.screens.main.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    fun injectTo(activity: MainActivity)
}
