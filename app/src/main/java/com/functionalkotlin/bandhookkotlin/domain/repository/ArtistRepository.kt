// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.RecommendationNotFound
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

interface ArtistRepository {
    fun getArtist(id: String): AsyncResult<Artist, ArtistNotFound>
    fun getRecommendedArtists(): AsyncResult<List<Artist>, RecommendationNotFound>
}
