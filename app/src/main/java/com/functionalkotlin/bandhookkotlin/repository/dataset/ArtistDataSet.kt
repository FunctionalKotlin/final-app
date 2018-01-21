// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository.dataset

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.RecommendationNotFound
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

interface ArtistDataSet {
    fun requestArtist(id: String): AsyncResult<Artist, ArtistNotFound>
    fun requestRecommendedArtists(): AsyncResult<List<Artist>, RecommendationNotFound>
}
