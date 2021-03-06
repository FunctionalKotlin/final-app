// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.album

import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.ui.screens.album.AlbumActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [(AlbumActivityModule::class)])
interface AlbumActivityComponent {
    fun injectTo(activity: AlbumActivity)
}
