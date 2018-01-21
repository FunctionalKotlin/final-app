// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.artist

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.mapper.ImageMapper
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

fun transform(artists: List<LastFmArtist>): List<Artist> =
    artists
        .filter { it.hasQualityInfo() }
        .mapNotNull { transform(it) }

fun transform(artist: LastFmArtist, imageMapper: ImageMapper = ImageMapper()) = artist.mbid?.let {
    Artist(it, artist.name, imageMapper.getMainImageUrl(artist.images), artist.bio?.content)
}

private fun LastFmArtist.hasQualityInfo(): Boolean =
    !hasEmptyMbid() && images != null && images.isNotEmpty()

private fun LastFmArtist.hasEmptyMbid(): Boolean = mbid.isNullOrEmpty()
