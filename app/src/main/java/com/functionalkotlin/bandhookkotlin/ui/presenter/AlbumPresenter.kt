// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetAlbumDetailInteractor
import com.functionalkotlin.bandhookkotlin.functional.fold
import com.functionalkotlin.bandhookkotlin.functional.runAsync
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.album.detail.transform
import com.functionalkotlin.bandhookkotlin.ui.presenter.base.Presenter
import com.functionalkotlin.bandhookkotlin.ui.view.AlbumView

open class AlbumPresenter(
    override val view: AlbumView,
    private val albumInteractor: GetAlbumDetailInteractor) : Presenter<AlbumView> {

    suspend fun init(albumId: String) {
        albumInteractor.getAlbum(albumId).runAsync {
            it.fold(
                onSuccess = { view.showAlbum(transform(it)) },
                onError = { view.showAlbumNotFound(it) })
        }
    }

}
