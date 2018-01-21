// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

interface AlbumRepository {
    fun getTopAlbums(artistId: String?, artistName: String?): List<Album>
    fun getAlbum(id: String): AsyncResult<Album, AlbumNotFound>
}
