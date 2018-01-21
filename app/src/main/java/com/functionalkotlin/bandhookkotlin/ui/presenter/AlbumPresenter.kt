// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetAlbumDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.AlbumEvent
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.AlbumDetailDataMapper
import com.functionalkotlin.bandhookkotlin.ui.view.AlbumView

open class AlbumPresenter(
        override val view: AlbumView,
        override val bus: Bus,
        val albumInteractor: GetAlbumDetailInteractor,
        val interactorExecutor: InteractorExecutor,
        val albumDetailMapper: AlbumDetailDataMapper) : Presenter<AlbumView> {

    open fun init(albumId: String) {
        val albumDetailInteractor = albumInteractor
        albumInteractor.albumId = albumId
        interactorExecutor.execute(albumDetailInteractor)
    }

    fun onEvent(event: AlbumEvent) {
        view.showAlbum(albumDetailMapper.transform(event.album))
    }
}
