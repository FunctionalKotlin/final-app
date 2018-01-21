// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.detail

import com.functionalkotlin.bandhookkotlin.di.ActivityModule
import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail.ArtistDetailDataMapper
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.ImageTitleDataMapper
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
    fun provideArtistDataMapper() = ArtistDetailDataMapper()

    @Provides
    @ActivityScope
    fun provideImageTitleDataMapper() = ImageTitleDataMapper()

    @Provides
    @ActivityScope
    fun provideActivityPresenter(
        view: ArtistView, bus: Bus,
        artistDetailInteractor: GetArtistDetailInteractor,
        topAlbumsInteractor: GetTopAlbumsInteractor,
        interactorExecutor: InteractorExecutor,
        detailDataMapper: ArtistDetailDataMapper,
        imageTitleDataMapper: ImageTitleDataMapper)
        = ArtistPresenter(
        view, bus, artistDetailInteractor, topAlbumsInteractor,
        interactorExecutor, detailDataMapper, imageTitleDataMapper)

    @Provides
    @ActivityScope
    fun provideAlbumsFragment() = AlbumsFragment()

    @Provides
    @ActivityScope
    fun provideBiographyFragment() = BiographyFragment()
}
