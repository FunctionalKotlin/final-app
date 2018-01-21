// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository.dataset

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

interface ArtistDataSet {

    fun requestRecommendedArtists(): List<Artist>
    fun requestArtist(id: String): Artist?
}
