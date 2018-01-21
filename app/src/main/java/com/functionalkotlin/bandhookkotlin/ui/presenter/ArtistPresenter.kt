// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistDetailEvent
import com.functionalkotlin.bandhookkotlin.functional.fold
import com.functionalkotlin.bandhookkotlin.functional.runAsync
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail.ArtistDetailDataMapper
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.ImageTitleDataMapper
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

open class ArtistPresenter(
    override val view: ArtistView,
    override val bus: Bus,
    val artistDetailInteractor: GetArtistDetailInteractor,
    val topAlbumsInteractor: GetTopAlbumsInteractor,
    val interactorExecutor: InteractorExecutor,
    val artistDetailMapper: ArtistDetailDataMapper,
    val albumsMapper: ImageTitleDataMapper) : Presenter<ArtistView>, AlbumsPresenter {

    open fun init(artistId: String) {
        val artistDetailInteractor = artistDetailInteractor
        artistDetailInteractor.id = artistId
        interactorExecutor.execute(artistDetailInteractor)

        launch(UI) {
            topAlbumsInteractor.getTopAlbums(artistId).runAsync {
                it.fold(
                    onSuccess = { view.showAlbums(albumsMapper.transformAlbums(it)) },
                    onError = { view.showAlbumsNotFound(it) })
            }
        }
    }

    fun onEvent(event: ArtistDetailEvent) {
        view.showArtist(artistDetailMapper.transform(event.artist))
    }

    override fun onAlbumClicked(item: ImageTitle) {
        view.navigateToAlbum(item.id)
    }
}
