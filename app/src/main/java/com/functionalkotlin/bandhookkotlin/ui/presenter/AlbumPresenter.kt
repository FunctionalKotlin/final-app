// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetAlbumDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.functional.fold
import com.functionalkotlin.bandhookkotlin.functional.runAsync
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.AlbumDetailDataMapper
import com.functionalkotlin.bandhookkotlin.ui.view.AlbumView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

open class AlbumPresenter(
    override val view: AlbumView,
    override val bus: Bus,
    val albumInteractor: GetAlbumDetailInteractor,
    val albumDetailMapper: AlbumDetailDataMapper) : Presenter<AlbumView> {

    open fun init(albumId: String) {
        launch(UI) {
            albumInteractor.getAlbum(albumId).runAsync {
                it.fold(
                    onSuccess = { view.showAlbum(albumDetailMapper.transform(it)) },
                    onError = { view.showAlbumNotFound(it) })
            }
        }
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

}
