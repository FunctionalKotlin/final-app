// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.artist

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImage
import com.functionalkotlin.bandhookkotlin.data.mapper.image.getMainImageUrl
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

fun transform(artists: List<LastFmArtist>): List<Artist> =
    artists
        .filter { it.hasQualityInfo() }
        .mapNotNull { transform(it) }

fun transform(
    artist: LastFmArtist, imageMapper: ((List<LastFmImage>?) -> String?) = ::getMainImageUrl) =
    artist.mbid
        ?.let { Artist(it, artist.name, imageMapper(artist.images), artist.bio?.content) }

private fun LastFmArtist.hasQualityInfo(): Boolean =
    !hasEmptyMbid() && images != null && images.isNotEmpty()

private fun LastFmArtist.hasEmptyMbid(): Boolean = mbid.isNullOrEmpty()
