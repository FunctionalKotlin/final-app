// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Event
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Interactor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.AlbumEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository

class GetAlbumDetailInteractor(val albumRepository: AlbumRepository) : Interactor {

    var albumId: String? = null

    override fun invoke(): Event {
        val id = albumId ?: throw IllegalStateException("Album id should be specified")

        val album = albumRepository.getAlbum(id)
        return AlbumEvent(album)
    }

}
