// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.data.mapper.artist.transform
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.RecommendationNotFound
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult
import com.functionalkotlin.bandhookkotlin.repository.dataset.ArtistDataSet

class CloudArtistDataSet(
    private val language: String, private val lastFmService: LastFmService) : ArtistDataSet {

    val coldplayMbid = "cc197bad-dc9c-440d-a5b5-d52ba2e14234"

    override fun requestArtist(id: String): AsyncResult<Artist, ArtistNotFound> =
        lastFmService.requestArtistInfo(id, language).asyncResult {
            transform(artist)
        }.orElse {
            ArtistNotFound(id)
        }

    override fun requestRecommendedArtists(): AsyncResult<List<Artist>, RecommendationNotFound> =
        lastFmService.requestSimilar(coldplayMbid).asyncResult {
            transform(similarArtists.artists)
        }.orElse { RecommendationNotFound }
}
