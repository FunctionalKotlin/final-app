// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Event
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Interactor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistDetailEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository

class GetArtistDetailInteractor(val artistRepository: ArtistRepository) : Interactor {

    var id: String? = null

    override fun invoke(): Event {
        val id = this.id ?: throw IllegalStateException("id can´t be null")
        val artist = artistRepository.getArtist(id)
        return ArtistDetailEvent(artist)
    }
}
