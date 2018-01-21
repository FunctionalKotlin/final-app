// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistDetailEvent
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.TopAlbumsEvent
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.ArtistDetailDataMapper
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.ImageTitleDataMapper
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView

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

        val topAlbumsInteractor = topAlbumsInteractor
        topAlbumsInteractor.artistId = artistId
        interactorExecutor.execute(this.topAlbumsInteractor)
    }

    fun onEvent(event: ArtistDetailEvent) {
        view.showArtist(artistDetailMapper.transform(event.artist))
    }

    fun onEvent(event: TopAlbumsEvent) {
        view.showAlbums(albumsMapper.transformAlbums(event.topAlbums))
    }

    override fun onAlbumClicked(item: ImageTitle) {
        view.navigateToAlbum(item.id)
    }
}
