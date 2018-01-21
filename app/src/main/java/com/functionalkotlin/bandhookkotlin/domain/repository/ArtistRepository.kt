// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

interface ArtistRepository {
    fun getRecommendedArtists(): List<Artist>
    fun getArtist(id: String): Artist
}
