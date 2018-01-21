// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.data.mapper.artist.transform
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.repository.dataset.ArtistDataSet

class CloudArtistDataSet(
    private val language: String, private val lastFmService: LastFmService) : ArtistDataSet {

    val coldplayMbid = "cc197bad-dc9c-440d-a5b5-d52ba2e14234"

    override fun requestRecommendedArtists(): List<Artist> =
        lastFmService.requestSimilar(coldplayMbid).unwrapCall {
            // Search for coldplay similar artists.
            transform(similarArtists.artists)
        } ?: emptyList()

    override fun requestArtist(id: String): Artist? =
        lastFmService.requestArtistInfo(id, language).unwrapCall {
            return transform(artist)
        }
}
