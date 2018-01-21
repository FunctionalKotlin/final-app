// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Event
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Interactor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.TopAlbumsEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository

class GetTopAlbumsInteractor(val albumRepository: AlbumRepository) : Interactor {

    var artistId: String? = null
    var artistName: String? = null

    override fun invoke(): Event {
        if (artistId == null && artistName == null) {
            throw IllegalStateException("Either mbid or name should be specified")
        }
        val albums = albumRepository.getTopAlbums(artistId, artistName)
        return TopAlbumsEvent(albums)
    }
}
