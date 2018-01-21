// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

class ArtistMapper(val imageMapper: ImageMapper = ImageMapper()) {

    fun transform(artists: List<LastFmArtist>): List<Artist> = artists.filter { artistHasQualityInfo(it) }.mapNotNull { transform(it) }

    fun transform(artist: LastFmArtist) = artist.mbid?.let {
        Artist(
            artist.mbid, artist.name, imageMapper.getMainImageUrl(artist.images),
            artist.bio?.content)
    }

    private fun artistHasQualityInfo(it: LastFmArtist): Boolean = !isArtistMbidEmpty(it) && it.images != null && it.images.isNotEmpty()

    private fun isArtistMbidEmpty(artist: LastFmArtist): Boolean = artist.mbid?.isEmpty() ?: true
}
