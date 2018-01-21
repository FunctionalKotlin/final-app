// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

class GetArtistDetailInteractor(private val artistRepository: ArtistRepository) {

    fun getArtist(artistId: String): AsyncResult<Artist, ArtistNotFound> =
        artistRepository.getArtist(artistId)

}
