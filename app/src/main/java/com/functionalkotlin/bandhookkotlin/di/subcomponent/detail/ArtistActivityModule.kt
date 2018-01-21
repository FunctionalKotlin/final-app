// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.detail

import com.functionalkotlin.bandhookkotlin.di.ActivityModule
import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.ui.presenter.ArtistPresenter
import com.functionalkotlin.bandhookkotlin.ui.screens.detail.AlbumsFragment
import com.functionalkotlin.bandhookkotlin.ui.screens.detail.ArtistActivity
import com.functionalkotlin.bandhookkotlin.ui.screens.detail.BiographyFragment
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView
import dagger.Module
import dagger.Provides

@Module
class ArtistActivityModule(activity: ArtistActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun provideArtistView(): ArtistView = activity as ArtistView

    @Provides
    @ActivityScope
    fun provideActivityPresenter(
        view: ArtistView, bus: Bus,
        artistDetailInteractor: GetArtistDetailInteractor,
        topAlbumsInteractor: GetTopAlbumsInteractor,
        interactorExecutor: InteractorExecutor)
        = ArtistPresenter(
        view, bus, artistDetailInteractor, topAlbumsInteractor, interactorExecutor)

    @Provides
    @ActivityScope
    fun provideAlbumsFragment() = AlbumsFragment()

    @Provides
    @ActivityScope
    fun provideBiographyFragment() = BiographyFragment()
}
