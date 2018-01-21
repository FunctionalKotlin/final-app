// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.data.mapper.album.transform
import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult
import com.functionalkotlin.bandhookkotlin.functional.transform
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet

class CloudAlbumDataSet(private val lastFmService: LastFmService) : AlbumDataSet {

    override fun requestAlbum(mbid: String): AsyncResult<Album, AlbumNotFound> =
        lastFmService.requestAlbum(mbid).asyncResult {
            transform(album)
        }.orElse {
            AlbumNotFound(mbid)
        }

    override fun requestTopAlbums(artistId: String): AsyncResult<List<Album>, TopAlbumsNotFound> =
        artistId.takeIf { it.isNotBlank() }.result().transform {
            it?.let {
                lastFmService.requestAlbums(it, "").unwrapCall {
                    transform(topAlbums.albums)
                }
            }
        }.orElse {
            TopAlbumsNotFound(artistId)
        }

}
