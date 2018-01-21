// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Event
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Interactor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistsEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository

class GetRecommendedArtistsInteractor(val artistRepository: ArtistRepository) : Interactor {

    override fun invoke(): Event {
        val artists = artistRepository.getRecommendedArtists()
        return ArtistsEvent(artists)
    }
}
