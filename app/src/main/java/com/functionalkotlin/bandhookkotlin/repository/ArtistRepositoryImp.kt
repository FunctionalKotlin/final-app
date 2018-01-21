// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.repository.dataset.ArtistDataSet

class ArtistRepositoryImp(val artistDataSets: List<ArtistDataSet>) : ArtistRepository {

    override fun getRecommendedArtists(): List<Artist> {
        for (dataSet in artistDataSets) {
            val result = dataSet.requestRecommendedArtists()
            if (result.isNotEmpty()) {
                return result
            }
        }

        return emptyList()
    }

    override fun getArtist(id: String): Artist {
        for (dataSet in artistDataSets) {
            val result = dataSet.requestArtist(id)
            if (result != null) {
                return result
            }
        }

        val empty = "empty"

        return Artist(empty, empty, empty)
    }

}
