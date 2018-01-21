// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.RecommendationNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult
import com.functionalkotlin.bandhookkotlin.functional.asError
import com.functionalkotlin.bandhookkotlin.functional.firstSuccessIn
import com.functionalkotlin.bandhookkotlin.repository.dataset.ArtistDataSet

class ArtistRepositoryImpl(private val artistDataSets: List<ArtistDataSet>) : ArtistRepository {

    override fun getRecommendedArtists(): AsyncResult<List<Artist>, RecommendationNotFound> =
        firstSuccessIn(
            list = artistDataSets,
            f = ArtistDataSet::requestRecommendedArtists,
            acc = RecommendationNotFound.asError())

    override fun getArtist(id: String): AsyncResult<Artist, ArtistNotFound> =
        firstSuccessIn(
            list = artistDataSets,
            f = { it.requestArtist(id) },
            acc = ArtistNotFound(id).asError())

}
