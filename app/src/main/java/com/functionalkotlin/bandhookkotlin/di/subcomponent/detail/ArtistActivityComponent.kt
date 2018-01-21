// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.detail

import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.ui.screens.detail.ArtistActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(
    ArtistActivityModule::class
))
interface ArtistActivityComponent {

    fun injectTo(activity: ArtistActivity)
}
