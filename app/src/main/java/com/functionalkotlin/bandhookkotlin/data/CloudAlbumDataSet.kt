// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.data.mapper.album.transform
import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.functional.*
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet

class CloudAlbumDataSet(val lastFmService: LastFmService) : AlbumDataSet {

    override fun requestAlbum(mbid: String): AsyncResult<Album, AlbumNotFound> =
        lastFmService.requestAlbum(mbid).asyncResult().bind { response ->
            response?.album
                ?.let { transform(it) }
                ?.result()
                ?: AlbumNotFound(mbid).asError()
        }

    override fun requestTopAlbums(artistId: String): List<Album> =
        artistId.takeIf { it.isNotBlank() }?.let {
            lastFmService.requestAlbums(it, "").unwrapCall {
                transform(topAlbums.albums)
            }
        } ?: emptyList()

}
