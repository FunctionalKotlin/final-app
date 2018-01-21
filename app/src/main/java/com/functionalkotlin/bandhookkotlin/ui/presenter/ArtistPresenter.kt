// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.functional.fold
import com.functionalkotlin.bandhookkotlin.functional.runAsync
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail.transform
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformAlbums
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

open class ArtistPresenter(
    override val view: ArtistView,
    private val artistDetailInteractor: GetArtistDetailInteractor,
    private val topAlbumsInteractor: GetTopAlbumsInteractor) :
        Presenter<ArtistView>, AlbumsPresenter {

    open fun init(artistId: String) {
        launch(UI) {
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
    }

    override fun onAlbumClicked(item: ImageTitle) {
        view.navigateToAlbum(item.id)
    }
}
