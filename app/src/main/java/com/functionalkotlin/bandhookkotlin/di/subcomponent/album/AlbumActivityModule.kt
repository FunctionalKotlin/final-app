// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.album

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.functionalkotlin.bandhookkotlin.di.ActivityModule
import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetAlbumDetailInteractor
import com.functionalkotlin.bandhookkotlin.ui.adapter.TracksAdapter
import com.functionalkotlin.bandhookkotlin.ui.presenter.AlbumPresenter
import com.functionalkotlin.bandhookkotlin.ui.screens.album.AlbumActivity
import com.functionalkotlin.bandhookkotlin.ui.view.AlbumView
import dagger.Module
import dagger.Provides

@Module
class AlbumActivityModule(activity: AlbumActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun provideAlbumView(): AlbumView = activity as AlbumView

    @Provides
    @ActivityScope
    fun provideLinearLayoutManager(context: Context) = LinearLayoutManager(context)

    @Provides
    @ActivityScope
    fun provideTracksAdapter() = TracksAdapter()

    @Provides
    @ActivityScope
    fun provideAlbumPresenter(
        view: AlbumView, albumInteractor: GetAlbumDetailInteractor) =
        AlbumPresenter(view, albumInteractor)
}
