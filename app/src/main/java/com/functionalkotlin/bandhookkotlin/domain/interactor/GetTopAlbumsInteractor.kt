// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Event
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Interactor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.TopAlbumsEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository

class GetTopAlbumsInteractor(val albumRepository: AlbumRepository) : Interactor {

    var artistId: String = ""

    override fun invoke(): Event =
        artistId.takeIf { it.isNotBlank() }?.let {
            TopAlbumsEvent(albumRepository.getTopAlbums(artistId))
        } ?: throw IllegalStateException("mbid cannot be blank")
}
