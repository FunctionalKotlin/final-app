// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbum
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbumDetail
import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

fun transform(albums: List<LastFmAlbum>): List<Album> =
    albums
        .filter { it.hasQualityInfo() }
        .mapNotNull { transform(it) }

fun transform(
    album: LastFmAlbumDetail, imageMapper: ImageMapper = ImageMapper(),
    trackMapper: TrackMapper = TrackMapper()) = album.mbid?.let {
        Album(
            it, album.name, Artist("", album.artist), imageMapper.getMainImageUrl(album.images),
            trackMapper.transform(album.tracks.tracks))
    }

fun transform(
    album: LastFmAlbum, artistMapper: ArtistMapper = ArtistMapper(),
    imageMapper: ImageMapper = ImageMapper(), trackMapper: TrackMapper = TrackMapper()) =
        album.mbid?.let {
            Album(
                it, album.name, artistMapper.transform(album.artist),
                imageMapper.getMainImageUrl(album.images),
                trackMapper.transform(album.tracks?.tracks))
        }

private fun LastFmAlbum.hasQualityInfo(): Boolean = !hasEmptyMbid() && images.isNotEmpty()

private fun LastFmAlbum.hasEmptyMbid(): Boolean = mbid.isNullOrEmpty()