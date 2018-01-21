// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.functional.fold
import com.functionalkotlin.bandhookkotlin.functional.runAsync
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail.transform
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformAlbums
import com.functionalkotlin.bandhookkotlin.ui.presenter.base.AlbumsPresenter
import com.functionalkotlin.bandhookkotlin.ui.presenter.base.Presenter
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView

open class ArtistPresenter(
    override val view: ArtistView,
    private val artistDetailInteractor: GetArtistDetailInteractor,
    private val topAlbumsInteractor: GetTopAlbumsInteractor) :
        Presenter<ArtistView>, AlbumsPresenter {

    suspend fun init(artistId: String) {
        topAlbumsInteractor.getTopAlbums(artistId).runAsync {
            it.fold(
                onSuccess = { view.showAlbums(transformAlbums(it)) },
                onError = { view.showAlbumsNotFound(it) })
        }

        artistDetailInteractor.getArtist(artistId).runAsync {
            it.fold(
                onSuccess = { view.showArtist(transform(it)) },
                onError = { view.showArtistNotFound(it) })
        }
    }

    override fun onAlbumClicked(item: ImageTitle) {
        view.navigateToAlbum(item.id)
    }
}
