// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbum
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbumDetail
import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist

class AlbumMapper(
    val artistMapper: ArtistMapper = ArtistMapper(), val imageMapper: ImageMapper = ImageMapper(),
    val trackMapper: TrackMapper = TrackMapper()) {

    fun transform(albums: List<LastFmAlbum>): List<Album> =
        albums.filter { albumHasQualityInfo(it) }.mapNotNull { transform(it) }

    private fun albumHasQualityInfo(album: LastFmAlbum): Boolean =
        !isAlbumMbidEmpty(album) && album.images.isNotEmpty()

    private fun isAlbumMbidEmpty(album: LastFmAlbum): Boolean = album.mbid?.isEmpty() ?: true

    fun transform(album: LastFmAlbumDetail) = album.mbid?.let {
            Album(album.mbid,
                    album.name,
                    Artist("", album.artist),
                    imageMapper.getMainImageUrl(album.images),
                    trackMapper.transform(album.tracks.tracks))
    }

    fun transform(album: LastFmAlbum) = album.mbid?.let {
            Album(album.mbid,
                    album.name,
                    artistMapper.transform(album.artist),
                    imageMapper.getMainImageUrl(album.images),
                    trackMapper.transform(album.tracks?.tracks))
    }
}
