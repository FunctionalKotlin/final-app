// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.RecommendationNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

class GetRecommendedArtistsInteractor(private val artistRepository: ArtistRepository) {

    fun getRecommendedArtists(): AsyncResult<List<Artist>, RecommendationNotFound> =
        artistRepository.getRecommendedArtists()

}
